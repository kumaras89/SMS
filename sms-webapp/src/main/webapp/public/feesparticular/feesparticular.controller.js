(function () {
    'use strict';

    angular
        .module('FeesParticular')
        .controller('FeesParticularListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout','$uibModal',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout, $uibModal) {
                $scope.editFeesParticular = function (userId) {
                    $state.go('home.feesparticular-detail', {id: userId});
                };

                $scope.deleteFeesParticular = function(feesParticular) {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        templateUrl: 'confirmation-popup.html',
                        controller: 'FeesParticularModalCtrl',
                        resolve: {
                            getFeesParticular: function () {
                                return feesParticular;
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

                $scope.createNewFeesParticular = function () {
                    $state.go('home.feesparticular-creation');
                };

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function ($defer, params) {
                        CrudService.feesParticularService.GetAll().then(function (res) {
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
        .controller('FeesParticularDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state',
            function ($scope, $stateParams, CrudService, FlashService, $state) {

                $scope.updateFeesParticular = function () {
                    CrudService.feesParticularService.Update($scope.feesParticular).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.feesparticular-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.feesparticular-list');
                };

                $scope.loadFeesParticular = function () {
                    CrudService.feesParticularService.GetById($stateParams.id).then(function (res) {
                        $scope.feesParticular = res
                    })
                }

                $scope.loadFeesParticular();
            }])
        .controller('FeesParticularCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state',
            function ($scope, CrudService, FlashService, $state) {

                $scope.createNewFeesParticular = function () {
                    CrudService.feesParticularService.Create($scope.feesParticular).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.feesparticular-list');
                    });

                }
            }])

        .controller("FeesParticularModalCtrl", function ($scope, $uibModalInstance, CrudService, getFeesParticular, FlashService, $state, getTableParams) {
            $scope.commonAttribute = getFeesParticular.name;
            $scope.tableParams = getTableParams;

            $scope.ok = function () {
                CrudService.feesParticularService.Delete(getFeesParticular.id).then(function(){
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
