'use strict';

angular.module('SecuredOperation', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.securedoperation-list', {
                templateUrl: 'securedoperation/securedoperation-list.html',
                controller: 'CrudCtrl',
                url: '/securedoperation-list'
            })
            .state('home.securedoperation-detail', {
                templateUrl: 'securedoperation/securedoperation-detail.html',
                controller: 'CrudCtrl',
                url: '/securedoperation-detail/:id'
            })
            .state('home.securedoperation-creation', {
                templateUrl: 'securedoperation/securedoperation-creation.html',
                controller: 'CrudCtrl',
                url: '/securedoperation-creation'
            })
    }]);

