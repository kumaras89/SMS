'use strict';
angular.module('Hotel', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });
    $stateProvider
        .state('home.hotel-list', {
            templateUrl: 'hotel/hotel-list.html',
            controller: 'HotelListCtrl',
            url: '/hotel-list'
        })
        .state('home.hotel-update', {
            templateUrl: 'hotel/hotel-update.html',
            controller: 'HotelUpdateCtrl',
            url: '/hotel-update/:id'
        })
        .state('home.hotel-registration', {
            templateUrl: 'hotel/hotel-registration.html',
            controller: 'HotelRegistrationCtrl',
            url: '/hotel-registration'
        })

        .state('home.hotel-detail', {
            templateUrl: 'hotel/hotel-detail.html',
            controller: 'HotelDetailCtrl',
            url: '/hotel-detail/:id'
        })
}]);