(function () {
    'use strict';

    angular
        .module('app')
        .controller('CrudCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout','$uibModal',
        function ($scope,$stateParams, CrudService, FlashService, $state, ngTableParams,  $timeout, $uibModal) {

            $scope.init = function(path, clearCachePaths) {
                $scope.path = path;
                $scope.service = CrudService.of(path);
                $scope.clearCachePaths = clearCachePaths;
            }

            $scope.clearCache = function() {
                if($scope.clearCachePaths) {
                    _.each($scope.clearCachePaths, function(path) {
                        
                    })
                }
            }

            $scope.goToEdit = function (id) {
                $state.go('home.'+$scope.path+'-detail',{id: id});
            };

            $scope.goToCreate = function () {
                $state.go('home.'+$scope.path+'-creation');
            };

            $scope.goToList = function () {
                $state.go('home.'+ $scope.path +'-list');
            }

            $scope.delete = function (id) {
                $scope.service.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.clearCache();
                    $scope.tableParams.reload()
                });
            };

            $scope.update = function () {
                $scope.service.Update($scope.entity).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $scope.goToList()
                    $scope.clearCache();
                });

            };

            $scope.save = function () {
                $scope.service.Create($scope.entity).then(function () {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $scope.goToList()
                    $scope.clearCache();
                });
            }

            $scope.loadEntity = function() {
                $scope.service.GetById($stateParams.id).then(function(res) {
                    $scope.entity = res;
                })
            }
            //this for using both Secured Operation and Role for deleting confirmation
            $scope.deleteRoleConfirmation = function(role) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'confirmation-popup.html',
                    controller: 'deleteRoleModalCtrl',
                    resolve: {
                        getRole: function () {
                            return role;
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

            $scope.deleteSecureConfirmation = function(so) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'confirmation-popup.html',
                    controller: 'deleteSecuredModalCtrl',
                    resolve: {
                        getSo: function () {
                            return so;
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
            
            $scope.tableParams = new ngTableParams({
                page: 1,            // show first pagez
                count: 10,          // count per page
                sorting: {
                    name: 'asc'     // initial sorting
                }
            }, {
                total: 0,           // length of data
                getData: function($defer, params) {
                    $scope.service.GetAll().then(function (res) {
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
    .controller("deleteRoleModalCtrl", function ($scope, $uibModalInstance, CrudService, getRole, FlashService, $state, getTableParams) {

        $scope.commonAttribue = getRole.name;
        $scope.tableParams = getTableParams;

        $scope.ok = function () {
            CrudService.roleService.Delete(getRole.id).then(function(){
                FlashService.Success("Successfuly Deleted !!", true);
                $scope.tableParams.reload();
            });
            $uibModalInstance.close();
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    })
    .controller("deleteSecuredModalCtrl", function ($scope, $uibModalInstance, CrudService, getSo, FlashService, $state, getTableParams) {

        $scope.commonAttribue = getSo.operation;
        $scope.tableParams = getTableParams;

        $scope.ok = function () {
            CrudService.securedOperationService.Delete(getSo.id).then(function(){
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
