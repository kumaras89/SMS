(function () {
    'use strict';

    angular
        .module('Branch')
        .controller('BranchListCtrl', ['$scope', 'CrudService', 'FlashService', '$state',
        function ($scope, CrudService, FlashService, $state) {

            $scope.editBranch = function (userId) {
                $state.go('home.branch-detail',{id: userId});
            };

            $scope.deleteBranch = function (id) {
                CrudService.branchService.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.loadBranches();
                });

            };

            $scope.createNewBranch = function () {
                $state.go('home.branch-creation');
            };

            $scope.loadBranches = function () {
                CrudService.branchService.GetAll().then(function(res) {
                    if(res.message) {
                        $scope.branches = []
                        FlashService.Error(res.message)
                    } else {
                        $scope.branches = res
                    }
                }, function() {
                    $scope.branches = []
                })
            }

            $scope.loadBranches()



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
