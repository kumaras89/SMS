'use strict';

angular.module('HotelTracker', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });

    $stateProvider
        .state('home.hoteltracker-creation', {
            templateUrl: 'hoteltracker/hoteltracker-creation.html',
            controller: 'HotelTrackerCreationCtrl',
            url: '/hoteltracker-creation'
        })

        .state('home.hoteltracker-list', {
            templateUrl: 'hoteltracker/hoteltracker-list.html',
            controller: 'HotelTrackerListCtrl',
            url: '/hoteltracker-list'
        })
        .state('home.hoteltracker-detail', {
            templateUrl: 'hoteltracker/hoteltracker-detail.html',
            controller: 'HotelTrackerDetailCtrl',
            url: '/hoteltracker-detail/:id'
        })

        .state('home.hoteltracker-update', {
            templateUrl: 'hoteltracker/hoteltracker-update.html',
            controller: 'HotelTrackerUpdateCtrl',
            url: '/hoteltracker-update/:id'
        })


}]);
