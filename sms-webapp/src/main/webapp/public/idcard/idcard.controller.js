(function () {
    'use strict';

    angular
        .module('IDCard')
        .controller('IDCardListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout', 'AdminService',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout, AdminService) {

                $scope.view = function (idCardId) {
                    $state.go('home.idcard-detail', {id: idCardId});
                };

                $scope.search = function () {
                    $scope.idcard = {};
                    if ($scope.identityCode !== '') {
                        $scope.idcard.identityCode = $scope.identityCode;
                    }
                    if ($scope.status !== '') {
                        $scope.idcard.status = $scope.status;
                    }
                    if ($scope.name !== '') {
                        $scope.idcard.name = $scope.name;
                    }
                    if ($scope.year !== '') {
                        $scope.idcard.year = $scope.year;
                    }
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
                                CrudService.idcardService.Search($scope.idcard).then(function (data) {
                                    if (data.message) {
                                        FlashService.Error(data.message)
                                    } else {
                                        $timeout(function () {
                                            params.total(data.length);
                                            $defer.resolve(data);
                                        }, 10);
                                    }
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
                }

                $scope.init();

            }])
        .controller('IDCardDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state', '$http',
            function ($scope, $stateParams, CrudService, FlashService, $state, $http) {

                $scope.updateStatus = function (id,status) {
                    $scope.idCardDetail.status = status;

                    $http.put('/idcard/' + id, $scope.idCardDetail).then(function(response) {
                        $state.go('home.idcard-search');
                    });
                }

                $scope.loadIDCard = function () {
                    CrudService.idcardService.GetById($stateParams.id).then(function (res) {
                        $scope.idCardDetail = res
                        $scope.src = '/document/download/' + $scope.idCardDetail.fmsId + '/photo.jpg';
                    })
                }

                $scope.loadIDCard();
            }]);
})();
