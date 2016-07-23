(function () {
    'use strict';

    angular
        .module('Hotel')
        .controller('HotelListCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout','CrudService',
            function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, CrudService) {
                
                $scope.createNewHotel = function () {
                    $state.go('home.hotel-registration');
                };

                $scope.hrList = function () {
                    $state.go('home.hotelhr-list');
                };
                $scope.createNewHr = function () {
                    $state.go('home.hotelhr-registration');
                };

               /* $scope.deleteHotel = function(id) {
                    CrudService.hotelService.Delete(id).then(function () {
                        FlashService.Success("Successfully Deleted !!", false);
                        $scope.tableParams.reload();
                    });
                };*/

                $scope.viewHotel = function (id) {
                    $state.go('home.hotel-detail',{id: id});
                };

                $scope.updateHotel = function (id) {
                    $state.go('home.hotel-update',{id: id});
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
                        CrudService.hotelService.GetAll().then(function (res) {
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
                $scope.getBranchDesc = AdminService.getBranchDesc;

            }])
        .controller('HotelRegistrationCtrl', ['$scope', 'FlashService','CrudService', '$state', 'AdminService','$location',
                function ($scope, FlashService, CrudService, $state, AdminService,$location) {

                    $scope.createNewHotel = function () {
                        $scope.hotel.status='CREATED';
                        var hotel = $.extend(hotel, $scope.hotel);
                        hotel.branchCode = AdminService.getBranchCode($scope.hotel.branchCode);
                        CrudService.hotelService.Create(hotel).then(function (res) {
                            if (res.message) {
                                FlashService.Error(res.message);
                            } else {
                                FlashService.Success("Successfuly Inserted !!", true);
                                $location.path('/home/hotel-list');
                            }
                        });
                    }

        }])
        .controller('HotelUpdateCtrl', ['$scope','$stateParams', 'FlashService','CrudService', 'ngTableParams', '$state', 'AdminService',
            function ($scope,$stateParams, FlashService,CrudService, ngTableParams, $state, AdminService) {
                $scope.updateHotel = function () {
                    var hotel = $.extend(hotel, $scope.hotel);
                    hotel.branchCode = AdminService.getBranchCode($scope.hotel.branch);
                    CrudService.hotelService.Update(hotel).then(function(){
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.hotel-list');
                    });
                };
                $scope.loadHotel = function() {
                    var loadHotelFunc= function(){
                    CrudService.hotelService.GetById($stateParams.id).then(function(res) {
                        var hotel = res;
                        hotel.branch = AdminService.getBranchDesc(res.branchCode);
                        $scope.hotel = hotel;
                    })
                }
                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                    $scope.branch= _.pluck(data,"name")
                    loadHotelFunc();
                })
                }

                $scope.loadHotel();
            }])

        .controller('HotelDetailCtrl', ['$scope', 'FlashService', 'ngTableParams','$stateParams', '$location', 'AdminService','CrudService',
            function ($scope, FlashService, ngTableParams, $stateParams,$location, AdminService, CrudService) {

                $scope.loadHotel = function () {

                    CrudService.hotelService.GetById($stateParams.id).then(function (res) {
                        $scope.hotel = res
                    })
                    CrudService.hotelHrService.GetAll().then(function (res) {
                        $scope.hrs=res;
                    })
                }

                $scope.getHotelHrs = function (hotelHrCode){
                    return AdminService.getHotelCode(hotelHrCode);
                };

                $scope.getBranchDesc = function (branchCode){
                    return AdminService.getBranchDesc(branchCode);
                };

                $scope.goToPayment = function(code) {
                    $location.path('/home/hotel-list');
                }

                $scope.loadHotel();

            }]);


})();     
    