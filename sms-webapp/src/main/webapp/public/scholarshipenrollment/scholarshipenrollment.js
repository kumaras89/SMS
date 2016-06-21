'use strict';

angular.module('ScholarshipEnrollment', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.scholarshipenrollment-list', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-list.html',
                controller: 'ScholarshipEnrollmentListCtrl',
                url: '/scholarshipenrollment-list'
            })
            .state('home.scholarshipenrollment-detail', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-detail.html',
                controller: 'ScholarshipEnrollmentDetailCtrl',
                url: '/scholarshipenrollment-detail/:id'
            })
            .state('home.scholarshipenrollment-creation', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-creation.html',
                controller: 'ScholarshipEnrollmentCreationCtrl',
                url: '/scholarshipenrollment-creation'
            })
    }]);
