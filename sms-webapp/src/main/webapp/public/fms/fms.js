'use strict';

angular.module('FMS', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.fms-list', {
                templateUrl: 'fms/fms-main.html',
                controller: 'FMSCtrl',
                url: '/fms/:category/:uploaderid'
            })
    }]);
