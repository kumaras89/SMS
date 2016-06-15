'use strict';

angular.module('FeesParticular', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.feesparticular-list', {
                templateUrl: 'feesparticular/feesparticular-list.html',
                controller: 'FeesParticularListCtrl',
                url: '/feesparticular-list'
            })
            .state('home.feesparticular-detail', {
                templateUrl: 'feesparticular/feesparticular-detail.html',
                controller: 'FeesParticularDetailCtrl',
                url: '/feesparticular-detail/:id'
            })
            .state('home.feesparticular-creation', {
                templateUrl: 'feesparticular/feesparticular-creation.html',
                controller: 'FeesParticularCreationCtrl',
                url: '/feesparticular-creation'
            })
    }]);
