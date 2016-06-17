(function () {
    'use strict';

    angular
        .module('app')
        .controller('CrudCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state',
        function ($scope,$stateParams, CrudService, FlashService, $state) {

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

            $scope.goToEdit = function (userId) {
                $state.go('/'+$scope.path+'-detail/' + userId);
            };

            $scope.goToCreate = function () {
                $state.go('/'+$scope.path+'-creation');
            };

            $scope.goToList = function () {
                $state.go('/'+ $scope.path +'-list');
            }

            $scope.delete = function (id) {
                $scope.service.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.loadEntities();
                    $scope.clearCache();
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

            $scope.loadEntities = function() {
                $scope.service.GetAll().then(function(res) {
                    if(res.message) {
                        $scope.entities = []
                        FlashService.Error(res.message)
                    } else {
                        $scope.entities = res
                    }
                }, function() {
                    $scope.entities = []
                })
            }
        }]);

})();
