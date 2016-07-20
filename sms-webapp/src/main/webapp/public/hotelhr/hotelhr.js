'use strict';

angular.module('HotelHr', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

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
