'use strict';

angular.module('Role', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.role-list', {
                templateUrl: 'role/role-list.html',
                controller: 'CrudCtrl',
                url: '/role-list'
            })
            .state('home.role-detail', {
                templateUrl: 'role/role-detail.html',
                controller: 'CrudCtrl',
                url: '/role-detail/:id'
            })
            .state('home.role-creation', {
                templateUrl: 'role/role-creation.html',
                controller: 'CrudCtrl',
                url: '/role-creation'
            })
    }]);
