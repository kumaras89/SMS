(function () {
    'use strict';

    angular
        .module('MarketingCommission')
        .controller('MarketingCommissionCreationCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, $stateParams, CrudService, FlashService, $state, AdminService) {

                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });
                }

                $scope.init();

                $scope.createCommission = function () {
                    CrudService.marketingCommissionService.Create($scope.marketingCommission).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.marketingcommission-list');
                    });
                }


            }])
        .controller('MarketingCommissionListCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'ngTableParams', 'AdminService',
            function ($scope, $stateParams, CrudService, FlashService, $state, ngTableParams, AdminService) {

                $scope.createMarketingCommission = function() {
                    $state.go('home.marketingcommission-creation');
                };

                $scope.editMarketingCommission = function (id) {
                    $state.go('home.marketingcommission-edit' ,{id: id});
                };
                $scope.viewMarketingCommission = function (id) {
                    $state.go('home.marketingcommission-detail' ,{id: id});
                };

                /*$scope.deleteMarketingCommission = function(id) {
                    CrudService.marketingCommissionService.Delete(id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfully Deleted !!", false);
                        $scope.tableParams.reload();
                    });
                };*/

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function($defer, params) {
                        CrudService.marketingCommissionService.GetAll().then(function (res) {
                            if (res.message) {
                                $scope.entities = []
                                FlashService.Error(res.message)
                            } else {
                                $scope.entities = res;
                            }
                            $timeout(function() {
                                params.total($scope.entities.length);
                                $defer.resolve($scope.entities);
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


            }])
        .controller('MarketingCommissionEditCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, $stateParams, CrudService, FlashService, $state, AdminService) {

                $scope.updateMarketingCommission = function () {
                    CrudService.marketingCommissionService.Update($scope.marketingCommission).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.marketingcommission-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.marketingcommission-list');
                };

                $scope.loadMarketingCommission = function () {
                    CrudService.marketingCommissionService.GetById($stateParams.id).then(function (res) {
                        $scope.marketingCommission = res
                    })
                }
                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });
                }

                $scope.init();
                $scope.loadMarketingCommission();


            }])
        .controller('MarketingCommissionDistributionCtrl', ['$scope', '$rootScope', '$stateParams', 'CrudService', 'FlashService', '$timeout', '$state', 'ngTableParams', 'AdminService','$http',
            function ($scope, $rootScope, $stateParams, CrudService, FlashService, $timeout, $state, ngTableParams, AdminService ,$http) {

                $scope.logInUserDet = '';
                $scope.logInUserDet = $rootScope.globals.currentUser.otherDetails.role;
                $scope.marketingEmpCode = $rootScope.globals.currentUser.otherDetails.marketingEmployee;


                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function($defer, params) {
                        $http.get('/marketingsplit/commissonsplitdetails/').then(function (res) {
                            $scope.entities = [];
                            if (res.message) {
                                $scope.entities = []
                                FlashService.Error(res.message)
                            } else {
                                if ($scope.logInUserDet == 'SUPER_ADMIN')
                                    $scope.entities = res.data;
                                else {
                                    _.forEach(res.data, function (commission) {
                                        if (commission.referencePersonCode == $scope.marketingEmpCode) {
                                            $scope.entities.push({
                                                referencePersonCode: commission.referencePersonCode,
                                                referencePersonName: commission.referencePersonName,
                                                applicationNumber: commission.applicationNumber,
                                                studentName:commission.studentName,
                                                amount:commission.amount
                                            })
                                        }
                                    })
                                }
                            }
                            $timeout(function() {
                                params.total($scope.entities.length);
                                $defer.resolve($scope.entities);
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

            }])

})();

