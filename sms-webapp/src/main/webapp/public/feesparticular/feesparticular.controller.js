(function () {
    'use strict';

    angular
        .module('FeesParticular')
        .controller('FeesParticularListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout) {
                $scope.editFeesParticular = function (userId) {
                    $state.go('home.feesparticular-detail', {id: userId});
                };

                $scope.deleteFeesParticular = function (id) {
                    CrudService.feesParticularService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
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
                        CrudService.feesParticularService.GetAll().then(function (data) {
                            if (data.message) {
                                $scope.feesParticulars = [];
                                FlashService.Error(data.message)
                            } else {
                                $timeout(function () {
                                    params.total(data.length);
                                    $defer.resolve(data);
                                }, 10);

                            }
                        }, function () {
                            $scope.feesParticulars = []
                        })
                    }
                });
            }])
        .controller('FeesParticularDetailCtrl', ['$scope', '$routeParams', 'CrudService', 'FlashService', '$state',
            function ($scope, $routeParams, CrudService, FlashService, $state) {

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
                    CrudService.feesParticularService.GetById($routeParams.id).then(function (res) {
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
            }]);

})();
