'use strict';

angular.module('Attendance', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });

    $stateProvider
        .state('home.attendance', {
            templateUrl: 'attendance/attendance.html',
            controller: 'AttendanceCtrl',
            url: '/attendance'
        })
        .state('home.attendance-detail', {
            templateUrl: 'attendance/attendance-detail.html',
            controller: 'AttendanceDetailCtrl',
            url: '/attendance-detail'
        })
}]);
