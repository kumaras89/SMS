'use strict';

angular.module('Branch', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.branch-list', {
                templateUrl: 'branch/branch-list.html',
                controller: 'BranchListCtrl',
                controllerAs: 'vm',
                url: '/branch-list'
            })
            .state('home.branch-detail', {
                templateUrl: 'branch/branch-detail.html',
                controller: 'BranchDetailCtrl',
                controllerAs: 'vm',
                url: '/branch-detail/:id'
            })

            .state('home.branch-creation', {
                templateUrl: 'branch/branch-creation.html',
                controller: 'BranchCreationCtrl',
                controllerAs: 'vm',
                url: '/branch-creation'
            })
    }]);
