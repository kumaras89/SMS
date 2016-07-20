(function () {
    'use strict';

    angular
        .module('HotelTracker')
        .controller('HotelTrackerCreationCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService','CrudService',
            function ($scope, FlashService, ngTableParams, $state, AdminService,CrudService) {


                $scope.branchChanged = function() {

                        return hoteltracker.branchCode == $scope.hoteltracker.branchCode;
                    }
                $scope.hotelChanged = function() {

                    return hoteltracker.hotelCode == $scope.hoteltracker.hotelCode;
                }
                

                $scope.init = function() {
                    AdminService.getHotels(function(data) {
                        $scope.hotels = data;
                    })

                    AdminService.getStudents(function(data) {
                        $scope.students = data;
                       
                    })

                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs = data;
                    })

                    AdminService.getBranches(function(data) {
                        $scope.branches = data;
                    })

                }

                $scope.init();


                $scope.hotelTrackerCreate = function () {
                    var hoteltracker = $.extend(hoteltracker, $scope.hoteltracker);
                    $scope.hoteltracker.status='CREATED';
                    CrudService.hotelTrackerService.Create(hoteltracker).then(function (res) {
                        if (res.message) {
                            FlashService.Error(res.message);
                        } else {
                            FlashService.Success("Successfuly Inserted !!", true);
                            $state.go('home.hoteltracker-list');
                        }
                    });
                }
            }])


})();