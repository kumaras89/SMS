'use strict';

angular.module('User', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.user-list', {
                templateUrl: 'user/user-list.html',
                controller: 'UserListCtrl',
                url: '/user-list'
            })
            .state('home.user-detail', {
                templateUrl: 'user/user-detail.html',
                controller: 'UserDetailCtrl',
                url: '/user-detail/:id'
            })
            .state('home.user-creation', {
                templateUrl: 'user/user-creation.html',
                controller: 'UserCreationCtrl',
                url: '/user-creation'
            })
    }]);
