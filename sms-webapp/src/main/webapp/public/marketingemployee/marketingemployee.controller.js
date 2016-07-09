(function () {
    'use strict';

    angular
        .module('MarketingEmployee')
        .controller('MarketingEmployeeListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
            function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout, $uibModal) {

                $scope.editMarketingEmployee = function (userId) {
                    $state.go('home.marketingemployee-detail' ,{id: userId});
                };

                $scope.deleteMarketingEmployee = function(marketingEmployee) {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        templateUrl: 'confirmation-popup.html',
                        controller: 'MarketingEmployeeCtrl',
                        resolve: {
                            getMarketingEmployee: function () {
                                return marketingEmployee;
                            },
                            getTableParams: function () {
                                return $scope.tableParams;
                            }
                        }
                    });

                    modalInstance.result.then(function () {
                    }, function () {
                        //$log.info('Modal dismissed at: ' + new Date());
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
                        CrudService.marketingEmployeeService.GetAll().then(function (res) {
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
                    AdminService.getUsers(function (data) {
                        $scope.users = data;
                    });
                }

                $scope.init();


                $scope.createNewMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.Create($scope.marketingEmployee).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.marketingemployee-list');
                    });
                }
            }])
        .controller("MarketingEmployeeCtrl", function ($scope, $uibModalInstance, CrudService, getMarketingEmployee, FlashService, $state, getTableParams) {
            $scope.commonAttribute = getMarketingEmployee.name;
            $scope.tableParams = getTableParams;

            $scope.ok = function () {
                CrudService.marketingEmployeeService.Delete(getMarketingEmployee.id).then(function(){
                    FlashService.Success("Successfuly Deleted !!", true);
                    $scope.tableParams.reload();
                });
                $uibModalInstance.close();
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        });


})();
