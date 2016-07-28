(function () {
    'use strict';

    angular
        .module('app')
        .controller('CrudCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
        function ($scope,$stateParams, CrudService, FlashService, $state, ngTableParams,  $timeout) {

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
            $scope.deleteRole = function(id) {
                CrudService.roleService.Delete(id).then(function(){
                    FlashService.Success("Successfuly Deleted !!", false);
                    $scope.tableParams.reload();
                });
            };

            $scope.deleteSecure = function(id) {
                CrudService.securedOperationService.Delete(id).then(function(){
                    FlashService.Success("Successfuly Deleted !!", false);
                    $scope.tableParams.reload();
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


        }]);
})();
