(function () {
    'use strict';

    angular
        .module('Scheme')
/*        .filter('mySum', function () {
            return function (items) {
                var sum = 0;
                angular.forEach(items, function (item, index) {
                    if (item.amount) {
                        sum += item.amount;
                    }
                })
                return sum;
            }
        })*/
        .controller('SchemeListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout','$uibModal',
            function ($scope, CrudService, FlashService, ngTableParams, $state, $timeout, $uibModal) {
                $scope.editScheme = function (userId) {
                    $state.go('home.scheme-detail' ,{id: userId});
                };

                $scope.deleteScheme = function (id) {
                    CrudService.schemeService.Delete(id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Deleted !!", false);
                        $scope.tableParams.reload();
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
        .controller('SchemeDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'SchemeService', 'FlashService', '$state', 'AdminService',
            function ($scope, $stateParams, CrudService, SchemeService, FlashService, $state, AdminService) {

                    $scope.calcTotal=function (items) {
                        $scope.total=0;
                        angular.forEach(items, function (item, index) {
                            if (item.amount) {
                                $scope.total += item.amount;
                            }
                        })
                    }
                                   
                $scope.updateScheme = function () {
                    CrudService.schemeService.Update($scope.scheme).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.scheme-list');
                    });

                };

                $scope.getFeesParticularDesc = function (feesParticularCode){
                    return AdminService.getFeesParticularDesc(feesParticularCode);
                };

                $scope.cancel = function () {
                    $state.go('home.scheme-list');
                };

                $scope.loadScheme = function () {
                    CrudService.schemeService.GetById($stateParams.id).then(function (res) {
                        $scope.scheme = res
                        $scope.calcTotal($scope.scheme.feesInfos);
                    })

                    SchemeService.getFeesParticulars(function (data) {
                        $scope.feesParticulars = data;
                    })
                }

                $scope.loadScheme();
            }])

        .controller('SchemeCreationCtrl', ['$scope', 'CrudService', 'SchemeService', 'FlashService', '$state','AdminService',
            function ($scope, CrudService, SchemeService, FlashService, $state, AdminService) {
                $scope.total=0;

                $scope.calcTotal=function (items) {
                    $scope.total=0;
                    angular.forEach(items, function (item, index) {
                        if (item.amount) {
                            $scope.total += item.amount;
                        }
                    })
                }

                $scope.createNewScheme = function () {

                    $scope.scheme.feesInfos = $scope.feesInfos;

                    CrudService.schemeService.Create($scope.scheme).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.scheme-list');
                    });
                }

                $scope.getFeesParticularDesc = function (feesParticularCode){
                    return AdminService.getFeesParticularDesc(feesParticularCode);
                };

                $scope.init = function () {
                    SchemeService.getFeesParticulars(function (data) {
                        $scope.feesParticulars = data;
                        $scope.feesInfos = [];
                        data.forEach(function (feesParticular) {
                            $scope.feesInfos.push({feesParticularCode: feesParticular.code, amount: 0});
                        });
                    })
                }

                $scope.init();
            }]);


})();
