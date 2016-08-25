(function () {
    'use strict';

    angular
        .module('Expense')

        .controller('ExpenseCreationCtrl',['$rootScope','$scope', 'CrudService', 'FlashService', '$state', 'AdminService','$stateParams',
            function ($rootScope, $scope, CrudService, FlashService, $state, AdminService, $stateParams ) {

                $scope.expense={};
                $scope.expense.userName='';
                $scope.expense.userName=$rootScope.globals.currentUser.username;
                $scope.total1=0;
                $scope.expense.expenseDetails = [];

                $scope.expense.expenseDetails.push({
                    reason: '',
                    amount: '',
                });

                $scope.addRow = function(index){
                    $scope.expense.expenseDetails.push({
                        reason: '',
                        amount: '',
                    });
                }
                $scope.removeRow = function (index) {
                    
                    $scope.expense.expenseDetails.splice(index, 1);
                    $scope.getTotal();
                };
                
                $scope.getTotal = function () {
                    var total = 0;
                    angular.forEach( $scope.expense.expenseDetails, function (expense) {
                        total = total + expense.amount;
                    })
                    $scope.total= total;
                }
                
                $scope.submitExpense = function () {
                    $scope.expense.branchCode= AdminService.getBranchCode($scope.branchName);
                    $scope.expense.totalAmount = $scope.total;
                    CrudService.expenseService.Create($scope.expense).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly submitted !!", true);
                        $state.go('home.expense-list');
                    });

                }
            }])
        .controller('ExpenseListCtrl', ['$scope', 'CrudService','AdminService', 'FlashService', '$location','$http', 'ngTableParams', '$timeout','$state',
            function ($scope, CrudService,AdminService, FlashService, $location,$http, ngTableParams, $timeout ,$state) {

                $scope.editExpense = function (id) {
                    $state.go('home.expense-edit',{id: id});
                };

                $scope.viewExpense = function (id) {
                    $state.go('home.expense-detail' , {id: id});
                }
                $scope.createNewExpense = function () {
                    $location.path('/home/expense-creation');
                };

                $scope.deleteExpense = function(id) {
                    CrudService.expenseService.Delete(id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfully Deleted !!", false);
                        $scope.tableParams.reload();
                    });
                };

                $scope.search = function () {
                    if($scope.searchCriteria.durationTo){
                        $scope.searchCriteria.durationTo= moment($scope.searchCriteria.durationTo).add(1,'days');
                    }
                    if ($scope.tableParams) {
                        $scope.tableParams.reload()
                    } else {
                        $scope.tableParams = new ngTableParams({
                            page: 1,            // show first pagez
                            count: 10,          // count per page
                            sorting: {
                                name: 'asc'     // initial sorting
                            }
                        }, {
                            total: 0,           // length of data
                            getData: function($defer, params) {
                                $http.post('/expense/search', $scope.searchCriteria).then(function(res) {
                                    var data = res.data;
                                    $timeout(function() {
                                        params.total(data.length);
                                        $defer.resolve(data);
                                    }, 10);
                                }, function() {
                                    $scope.entities = []
                                    $timeout(function() {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        });
                    }
                }

                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                })
                AdminService.getUsers(function (data) {
                    $scope.users = _.pluck(data,"name");
                })

                $scope.getBranchDesc = AdminService.getBranchDesc

            }])

        .controller('ExpenseEditCtrl', ['$rootScope','$scope', 'CrudService', 'FlashService', '$state', 'AdminService','$stateParams','$http',
            function ($rootScope, $scope, CrudService, FlashService, $state, AdminService, $stateParams, $http) {

                $scope.expense={};
                $scope.total1=0;
                $scope.expense.expenseDetails = [];

                $scope.expense.expenseDetails.push({
                    reason: '',
                    amount: '',
                });

                $scope.addRow = function(index){
                    $scope.expense.expenseDetails.push({
                        reason: '',
                        amount: '',
                    });
                }
                $scope.removeRow = function (index,id) {
                    $http.delete('/expense/expenseDetails/' + id);
                    $scope.expense.expenseDetails.splice(index, 1);
                    $scope.getTotal();
                };

                $scope.getTotal = function () {
                    var total = 0;
                    angular.forEach( $scope.expense.expenseDetails, function (expense) {
                        total = total + expense.amount;
                    })
                    $scope.expense.totalAmount= total;
                }

                $scope.updateExpense = function () {
                    $scope.expense.branchCode= AdminService.getBranchCode($scope.branchName);
                    CrudService.expenseService.Create($scope.expense).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Updated !!", true);
                        $state.go('home.expense-list');
                    });

                }

                $scope.loadExpense = function () {
                    $http.get('/expense/'+$stateParams.id).then(function (res) {
                        $scope.expense = res.data
                        $scope.branchName = AdminService.getBranchDesc(res.data.branchCode);
                        $scope.expense.expenseDate =  new Date(res.data.expenseDate);
                        $scope.expense.totalAmount =  res.data.totalAmount;
                    })
                }

                $scope.loadExpense();
            }])
        .controller('ExpenseDetailCtrl', ['$scope', '$stateParams', 'CrudService', '$http',
            function ($scope, $stateParams, CrudService, $http) {
                $scope.expense={};

                $scope.loadExpense = function () {

                    $http.get('/expense/'+$stateParams.id).then(function (res) {
                        $scope.expense = res.data

                    })

                }
                $scope.loadExpense();

            }])
})();