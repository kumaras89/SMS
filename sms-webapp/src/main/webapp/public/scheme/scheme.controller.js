(function () {
    'use strict';

    angular
        .module('Scheme')
        .filter('mysum', function () {
            return function (items) {
                var sum = 0;
                items.forEach(function (item) {
                    console.log(item.weightage);
                    if (item.weightage) {
                        sum += item.weightage;
                    }
                })
                return sum;
            }
        })
        .controller('SchemeListCtrl', ['$scope', 'CrudService', 'FlashService', '$location',
            function ($scope, CrudService, FlashService, $location) {
                $scope.editScheme = function (userId) {
                    $location.path('/scheme-detail/' + userId);
                };

                $scope.deleteScheme = function (id) {
                    CrudService.schemeService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                        $scope.loadSchemes();
                    });

                };

                $scope.createNewScheme = function () {
                    $location.path('/scheme-creation');
                };

                $scope.loadSchemes = function () {
                    CrudService.schemeService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.schemes = []
                            FlashService.Error(res.message)
                        } else {
                            $scope.schemes = res
                        }
                    }, function () {
                        $scope.schemes = []
                    })
                }

                $scope.loadSchemes()


            }])
        .controller('SchemeDetailCtrl', ['$scope', '$routeParams', 'CrudService', 'SchemeService', 'FlashService', '$location',
            function ($scope, $routeParams, CrudService, SchemeService, FlashService, $location) {

                $scope.updateScheme = function () {
                    CrudService.schemeService.Update($scope.scheme).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $location.path('/scheme-list');
                    });

                };

                $scope.cancel = function () {
                    $location.path('/scheme-list');
                };

                $scope.loadScheme = function () {
                    CrudService.schemeService.GetById($routeParams.id).then(function (res) {
                        $scope.scheme = res
                    })
                }

                $scope.loadScheme();
            }])
        .controller('SchemeCreationCtrl', ['$scope', 'CrudService', 'SchemeService', 'FlashService', '$location',
            function ($scope, CrudService, SchemeService, FlashService, $location) {

                $scope.createNewScheme = function () {
                    CrudService.schemeService.Create($scope.scheme).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $location.path('/scheme-list');
                    });
                }

                $scope.init = function () {
                    SchemeService.getFeesCategories(function (data) {
                        $scope.feesCategories = data;
                    })
                }

                $scope.init();
            }]);

})();
