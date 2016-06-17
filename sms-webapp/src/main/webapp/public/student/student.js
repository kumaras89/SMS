'use strict';

angular.module('Student', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.student-list', {
                templateUrl: 'student/student-list.html',
                controller: 'StudentListCtrl',
                url: '/student-list'
            })
            .state('home.student-detail', {
                templateUrl: 'student/student-detail.html',
                controller: 'StudentDetailCtrl',
                url: '/student-detail/:id'
            })
            .state('home.student-creation', {
                templateUrl: 'student/student-creation.html',
                controller: 'StudentCreationCtrl',
                url: '/student-creation'
            })
    }]);
