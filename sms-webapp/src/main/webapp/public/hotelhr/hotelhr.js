'use strict';

angular.module('HotelHr', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable'])
    .directive("hotelsHr", ['$rootScope', 'AdminService', '$timeout', function ($rootScope, AdminService, $timeout) {
        return {
            restrict: 'A',
            scope: {
                ht: '=ht'
            },
            controller: function ($scope) {
                var logInUserDet = $rootScope.globals.currentUser.otherDetails;
                var branchCode = $rootScope.globals.currentUser.otherDetails.branch;

                AdminService.getHotels(function (data) {
                    $scope.hotels=[];
                    if (logInUserDet.role == 'SUPER_ADMIN'){
                        $scope.hotels = _.pluck(data,"hotelName");
                        $scope.hotels = _.pluck(data,"hotelCode");
                    }
                    else {
                        _.forEach(data, function (hr) {
                            if (hr.branchCode == branchCode) {
                                $scope.hotels.push({
                                    hotelName: hr.hotelName,
                                    hotelCode: hr.hotelCode
                                })
                            }
                        })
                    }
                });
            },
            template: function () {
                if ($rootScope.globals.currentUser.otherDetails.role == 'SUPER_ADMIN') {
                    return '<select  ng-model="ht" ng-options="hotel.hotelCode as hotel.hotelName for hotel in hotels track by hotelCode" name="hotel" required> </select>' +
                        '<span class="error" ng-messages="hotelhrForm.hr.$error" ng-if="hotelhrForm.hr.$touched">' +
                        '<span ng-message="required">Hotel is required.</span> </span>'

                } else {
                    return '<select  ng-model="ht" ng-options="hotel.hotelCode as hotel.hotelName for hotel in hotels track by hotelCode" name="hotel" required> </select>' +
                        '<span class="error" ng-messages="hotelhrForm.hr.$error" ng-if="hotelhrForm.hr.$touched">' +
                        '<span ng-message="required">Hotel is required.</span> </span>'
                }
            }
        }
    }])
    .config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });


        $stateProvider
            .state('home.hotelhr-list', {
                templateUrl: 'hotelhr/hotel-hr-list.html',
                controller: 'HotelHrListCtrl',
                url: '/hotelhr-list'
            })
            .state('home.hotelhr-update', {
                templateUrl: 'hotelhr/hotel-hr-update.html',
                controller: 'HotelHrUpdateCtrl',
                url: '/hotelhr-update/:id'
            })
            .state('home.hotelhr-registration', {
                templateUrl: 'hotelhr/hotel-hr-registration.html',
                controller: 'HotelHrRegistrationCtrl',
                url: '/hotelhr-registration'
            })
    }]);
