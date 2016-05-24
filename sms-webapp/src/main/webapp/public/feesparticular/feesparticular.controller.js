(function () {
    'use strict';

    angular
        .module('FeesParticular')
        .controller('FeesParticularListCtrl', ['$scope', 'CrudService', 'FlashService', '$location',
        function ($scope, CrudService, FlashService, $location) {
            $scope.editFeesParticular = function (userId) {
                $location.path('/feesparticular-detail/' + userId);
            };

            $scope.deleteFeesParticular = function (id) {
                CrudService.feesParticularService.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.loadFeesParticulars();
                });

            };

            $scope.createNewFeesParticular = function () {
                $location.path('/feesparticular-creation');
            };

            $scope.loadFeesParticulars = function () {
                CrudService.feesParticularService.GetAll().then(function(res) {
                    if(res.message) {
                        $scope.feesParticulars = []
                        FlashService.Error(res.message)
                    } else {
                        $scope.feesParticulars = res
                    }
                }, function() {
                    $scope.feesParticulars = []
                })
            }

            $scope.loadFeesParticulars()



        }])
        .controller('FeesParticularDetailCtrl', ['$scope', '$routeParams', 'CrudService','FlashService', '$location',
        function ($scope, $routeParams, CrudService,FlashService, $location) {

            $scope.updateFeesParticular = function () {
                CrudService.feesParticularService.Update($scope.feesParticular).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/feesparticular-list');
                });

            };

            $scope.cancel = function () {
                $location.path('/feesparticular-list');
            };

            $scope.loadFeesParticular = function() {
                CrudService.feesParticularService.GetById($routeParams.id).then(function(res) {
                    $scope.feesParticular = res
                })
            }

            $scope.loadFeesParticular();
        }])
        .controller('FeesParticularCreationCtrl', ['$scope', 'CrudService','FlashService', '$location',
        function ($scope, CrudService,FlashService, $location) {

            $scope.createNewFeesParticular = function () {
                CrudService.feesParticularService.Create($scope.feesParticular).then(function () {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $location.path('/feesparticular-list');
                });

            }
        }]);

})();
