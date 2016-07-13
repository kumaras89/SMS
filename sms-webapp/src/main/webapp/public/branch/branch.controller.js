(function () {
    'use strict';

    angular
        .module('Branch')
        .controller('BranchListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
        function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout , $uibModal) {

            $scope.editBranch = function (userId) {
                $state.go('home.branch-detail',{id: userId});
            };
            
            $scope.deleteBranch = function(id) {
                CrudService.branchService.Delete(id).then(function(){
                    FlashService.Success("Successfully Deleted !!", false);
                    $scope.tableParams.reload();
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
        }]);
        


})();
