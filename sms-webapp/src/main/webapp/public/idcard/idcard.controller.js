(function () {
    'use strict';

    angular
        .module('IDCard')
        .controller('IDCardListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout) {

                $scope.view = function (idCardId,uploaderId) {
                    $state.go('home.idcard-detail',{id:idCardId, userCode: uploaderId});
                };

                $scope.search = function () {
                    $scope.idcard = {};
                    if($scope.uploaderId !== ''){
                        $scope.idcard.uploaderId = $scope.uploaderId;
                    }
                    if($scope.status !== ''){
                        $scope.idcard.status = $scope.status;
                    }
                    if($scope.uploaderCategory !== ''){
                        $scope.idcard.uploaderCategory = $scope.uploaderCategory;
                    }
                    if($scope.year !== ''){
                        $scope.idcard.year = $scope.year;
                    }

                    $scope.tableParams.reload()
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
            }])
        .controller('IDCardDetailCtrl', ['$scope', '$stateParams', 'CrudService','FlashService', '$state',
            function ($scope, $stateParams, CrudService,FlashService, $state) {

                $scope.loadIDCard = function() {

                    $scope.src ="http://1.bp.blogspot.com/-vdi34NMKeQo/Urcomf2Jh9I/AAAAAAAAAFs/wv0JOotax64/s1600/passport+size+copy.jpg";

                    CrudService.idcardService.GetById($stateParams.id).then(function(res) {
                        $scope.idCardDetail = res
                    })
                }

                $scope.loadIDCard();
            }]);
})();
