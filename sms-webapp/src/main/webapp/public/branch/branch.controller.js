(function () {
    'use strict';

    angular
        .module('Branch')
        .controller('BranchListCtrl', ['$scope', 'CrudService', 'FlashService', '$location', '$timeout', '$state',
        function ($scope, CrudService, FlashService, $location, $timeout, $state) {

            $timeout(function(){
                $('#branchTables').dataTable( {
                    responsive: true
                } );
            }, 0, false);


            $scope.editBranch = function (userId) {
                $location.path('home.branch-detail({id: userId})');
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
        .controller('BranchDetailCtrl', ['$scope', '$routeParams', 'CrudService','FlashService', '$location',
        function ($scope, $routeParams, CrudService,FlashService, $location) {

            $scope.updateBranch = function () {
                CrudService.branchService.Update($scope.branch).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/branch-list');
                });

            };

            $scope.cancel = function () {
                $location.path('/branch-list');
            };

            $scope.loadBranch = function() {
                CrudService.branchService.GetById($routeParams.id).then(function(res) {
                    $scope.branch = res
                })
            }

            $scope.loadBranch();
        }])
        .controller('BranchCreationCtrl', ['$scope', 'CrudService','FlashService', '$location',
        function ($scope, CrudService,FlashService, $location) {

            $scope.createNewBranch = function () {
                CrudService.branchService.Create($scope.branch).then(function () {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $location.path('/branch-list');
                });

            }
        }]);

})();
