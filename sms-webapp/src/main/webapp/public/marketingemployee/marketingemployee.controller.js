(function () {
    'use strict';

    angular
        .module('MarketingEmployee')
        .controller('MarketingEmployeeListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout',
            function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout) {

                $scope.editMarketingEmployee = function (userId) {
                    $state.go('home.marketingemployee-detail' ,{id: userId});
                };

                $scope.deleteMarketingEmployee = function (id) {
                    CrudService.marketingEmployeeService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                    });

                };

                $scope.createNewMarketingEmployee = function () {
                    $state.go('home.marketingemployee-creation');
                };

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function($defer, params) {
                        CrudService.marketingEmployeeService.GetAll().then(function(data) {
                            if(data.message) {
                                $scope.marketingEmployees = [];
                                FlashService.Error(data.message)
                            } else {
                                $timeout(function() {
                                    params.total(data.length);
                                    $defer.resolve(data);
                                }, 10);

                            }
                        }, function() {
                            $scope.marketingEmployees = []
                        })
                    }
                });
            }])
        .controller('MarketingEmployeeDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, $stateParams, CrudService, FlashService, $state, AdminService) {

                $scope.updateMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.Update($scope.marketingEmployee).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.marketingemployee-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.marketingemployee-list');
                };

                $scope.loadMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.GetById($stateParams.id).then(function (res) {
                        $scope.marketingEmployee = res
                    })
                }

                $scope.init = function () {
                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });

                    $scope.loadMarketingEmployee();
                }

                $scope.init();

            }])
        .controller('MarketingEmployeeCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, CrudService, FlashService, $state, AdminService) {

                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });
                }

                $scope.init();


                $scope.createNewMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.Create($scope.marketingEmployee).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.marketingemployee-list');
                    });
                }
            }]);

})();
