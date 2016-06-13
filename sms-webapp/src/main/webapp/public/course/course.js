'use strict';

angular.module('Course', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.course-list', {
                templateUrl: 'course/course-list.html',
                controller: 'CourseListCtrl',
                url: '/course-list'
            })
            .state('home.course-detail', {
                templateUrl: 'course/course-detail.html',
                controller: 'CourseDetailCtrl',
                url: '/course-detail/:id'
            })
            .state('home.course-creation', {
                templateUrl: 'course/course-creation.html',
                controller: 'CourseCreationCtrl',
                url: '/course-creation'
            })
    }]);
