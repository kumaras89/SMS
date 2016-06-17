'use strict';

angular.module('Scheme', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.scheme-list', {
                templateUrl: 'scheme/scheme-list.html',
                controller: 'SchemeListCtrl',
                url: '/scheme-list'
            })
            .state('home.scheme-detail', {
                templateUrl: 'scheme/scheme-detail.html',
                controller: 'SchemeDetailCtrl',
                url: '/scheme-detail/:id'
            })
            .state('home.scheme-creation', {
                templateUrl: 'scheme/scheme-creation.html',
                controller: 'SchemeCreationCtrl',
                url: '/scheme-creation'
            })
    }]);
