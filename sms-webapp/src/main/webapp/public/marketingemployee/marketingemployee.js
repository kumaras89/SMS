'use strict';

angular.module('MarketingEmployee', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.marketingemployee-list', {
                templateUrl: 'marketingemployee/marketingemployee-list.html',
                controller: 'MarketingEmployeeListCtrl',
                url: '/marketingemployee-list'
            })
            .state('home.marketingemployee-detail', {
                templateUrl: 'marketingemployee/marketingemployee-detail.html',
                controller: 'MarketingEmployeeDetailCtrl',
                url: '/marketingemployee-detail/:id'
            })
            .state('home.marketingemployee-creation', {
                templateUrl: 'marketingemployee/marketingemployee-creation.html',
                controller: 'MarketingEmployeeCreationCtrl',
                url: '/marketingemployee-creation'
            })
    }]);

