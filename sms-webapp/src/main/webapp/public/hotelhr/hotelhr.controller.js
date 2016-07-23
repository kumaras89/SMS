(function () {
    'use strict';

    angular
        .module('HotelHr')
        .controller('HotelHrListCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout', 'CrudService',
            function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, CrudService) {

                $scope.createNewHr = function () {
                    $state.go('home.hotelhr-registration');
                };

                $scope.updateHotelHr = function (id) {
                    $state.go('home.hotelhr-update', {id: id});
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
                        CrudService.hotelHrService.GetAll().then(function (res) {
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

                $scope.getHotelName = AdminService.getHotelName


            }])
        .controller('HotelHrRegistrationCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout', 'CrudService',
            function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, CrudService) {

                $scope.createNewHr = function () {
                    CrudService.hotelHrService.Create($scope.hotelhr).then(function (res) {
                        if(res.message) {
                            FlashService.Error(res.message);
                        } else {
                            FlashService.Success("Successfuly Inserted !!", true);
                            $state.go('home.hotelhr-list');
                        }
                    });

                };
                $scope.init = function () {
                    AdminService.getHotels(function (data) {
                        $scope.hotels= data;
                    })
                };
                $scope.init();
            }])
        .controller('HotelHrUpdateCtrl', ['$scope','$stateParams', 'FlashService','CrudService', 'ngTableParams', '$state', 'AdminService',
            function ($scope,$stateParams, FlashService,CrudService, ngTableParams, $state, AdminService) {
                $scope.updateHotelHr = function () {
                    CrudService.hotelHrService.Update($scope.hotelhr).then(function(){
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.hotelhr-list');
                    });
                };
                $scope.loadHotelHr = function() {
                    var loadHotelHrFunc= function(){
                        CrudService.hotelHrService.GetById($stateParams.id).then(function(res) {
                            var hotelhr = res;
                            hotelhr.hotelCode = AdminService.getHotelName(res.hotelCode);
                            $scope.hotelhr = hotelhr;
                        })
                    }
                    AdminService.getHotels(function(data) {
                        $scope.hotels= data;
                        loadHotelHrFunc();
                    })
                }

                $scope.loadHotelHr();
            }]);

})();







