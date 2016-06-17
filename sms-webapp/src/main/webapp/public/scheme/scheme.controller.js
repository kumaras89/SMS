﻿(function () {
    'use strict';

    angular
        .module('Scheme')
        .filter('mysum', function () {
            return function (items) {
                var sum = 0;
                items.forEach(function (item) {
                    if (item.weightage) {
                        sum += item.weightage;
                    }
                })
                return sum;
            }
        })
        .controller('SchemeListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout',
            function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout) {
                $scope.editScheme = function (userId) {
                    $state.go('home.scheme-detail' ,{id: userId});
                };

                $scope.deleteScheme = function (id) {
                    CrudService.schemeService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                    });

                };

                $scope.createNewScheme = function () {
                    $state.go('home.scheme-creation');
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
                    CrudService.schemeService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.schemes = []
                            FlashService.Error(res.message)
                        } else {
                            $timeout(function() {
                                params.total(res.length);
                                $defer.resolve(res);
                            }, 10);

                        }
                    }, function() {
                        $scope.schemes = []
                    })
                    }
                });
            }])
        .controller('SchemeDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'SchemeService', 'FlashService', '$state',
            function ($scope, $stateParams, CrudService, SchemeService, FlashService, $state) {

                $scope.updateScheme = function () {
                    CrudService.schemeService.Update($scope.scheme).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.scheme-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.scheme-list');
                };

                $scope.loadScheme = function () {
                    CrudService.schemeService.GetById($stateParams.id).then(function (res) {
                        $scope.scheme = res
                    })

                    SchemeService.getFeesParticulars(function (data) {
                        $scope.feesParticulars = data;
                    })
                }

                $scope.loadScheme();
            }])
        .controller('SchemeCreationCtrl', ['$scope', 'CrudService', 'SchemeService', 'FlashService', '$state',
            function ($scope, CrudService, SchemeService, FlashService, $state) {

                $scope.createNewScheme = function () {

                    $scope.scheme.schemeFeesInfos = $scope.schemeFeesInfos;

                    CrudService.schemeService.Create($scope.scheme).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.scheme-list');
                    });
                }

                $scope.init = function () {
                    SchemeService.getFeesParticulars(function (data) {
                        $scope.feesParticulars = data;
                        $scope.schemeFeesInfos = [];
                        data.forEach(function (feesParticular) {
                            $scope.schemeFeesInfos.push({feesParticularCode: feesParticular.code, weightage: 0});
                        });
                    })
                }

                $scope.init();
            }]);

})();
