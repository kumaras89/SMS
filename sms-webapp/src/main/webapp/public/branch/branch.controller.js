(function () {
    'use strict';

    angular
        .module('Branch')
        .controller('BranchListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
        function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout , $uibModal) {

            $scope.editBranch = function (userId) {
                $state.go('home.branch-detail',{id: userId});
            };
            
            $scope.deleteBranch = function(branch) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'confirmation-popup.html',
                    controller: 'BranchModalCtrl',
                    resolve: {
                        getBranch: function () {
                            return branch;
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

            $scope.createNewBranch = function () {
                $state.go('home.branch-creation');
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
                    CrudService.branchService.GetAll().then(function (res) {
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
        .controller('BranchDetailCtrl', ['$scope', '$stateParams', 'CrudService','FlashService', '$state',
        function ($scope, $stateParams, CrudService,FlashService, $state) {

            $scope.updateBranch = function () {
                CrudService.branchService.Update($scope.branch).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $state.go('home.branch-list');
                });

            };

            $scope.cancel = function () {
                $state.go('home.branch-list');
            };

            $scope.loadBranch = function() {
                CrudService.branchService.GetById($stateParams.id).then(function(res) {
                    $scope.branch = res
                })
            }

            $scope.loadBranch();
        }])
        .controller('BranchCreationCtrl', ['$scope', 'CrudService','FlashService', '$state',
        function ($scope, CrudService,FlashService, $state) {

            $scope.createNewBranch = function () {
                CrudService.branchService.Create($scope.branch).then(function () {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $state.go('home.branch-list');
                });

            }
        }])
        .controller("BranchModalCtrl", function ($scope, $uibModalInstance, CrudService, getBranch, FlashService, $state, getTableParams) {
            $scope.commonAttribute = getBranch.name;
            $scope.tableParams = getTableParams;

            $scope.ok = function () {
                CrudService.branchService.Delete(getBranch.id).then(function(){
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
