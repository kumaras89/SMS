﻿(function () {
    'use strict';

    angular
        .module('IDCard')
        .controller('IDCardListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout', 'AdminService',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout, AdminService) {

                $scope.view = function (idCardId) {
                    $state.go('home.idcard-detail', {id: idCardId});
                };

                $scope.search = function () {
                    if ($scope.tableParams) {
                        $scope.tableParams.reload()
                    } else {
                        $scope.tableParams = new ngTableParams({
                            page: 1,            // show first pagez
                            count: 10,          // count per page
                            sorting: {
                                name: 'asc'     // initial sorting
                            }
                        }, {
                            total: 0,           // length of data
                            getData: function ($defer, params) {
                                CrudService.idcardService.Search($scope.searchCriteria).then(function (data) {
                                    $timeout(function () {
                                        params.total(data.length);
                                        $defer.resolve(data);
                                    }, 10);
                                }, function () {
                                    $scope.entities = []
                                    $timeout(function () {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        })
                    }

                };

                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });
                    $scope.searchCriteria = {}
                }

                $scope.init();

            }])
        .controller('IDCardDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', '$http', '$window',
            function ($scope, $stateParams, CrudService, FlashService, $state, $http,$window) {

                $scope.updateStatus = function (id,status) {
                    $scope.idCardDetail.status = status;

                    $http.put('/idcard/' + id, $scope.idCardDetail).then(function(response) {
                        $state.go('home.idcard-search');
                    });
                }

                $scope.loadIDCard = function () {
                    CrudService.idcardService.GetById($stateParams.id).then(function (res) {
                        $scope.idCardDetail = res
                        $scope.src = '/document/download/' + $scope.idCardDetail.fmsId + '/'+$scope.idCardDetail.name +'.jpg';
                    })
                }

                $scope.download = function () {
                    $window.open($scope.src);
                }

                $scope.loadIDCard();
            }]);
})();
