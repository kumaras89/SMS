'use strict';
angular.module('MarketingCommission', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });
    $stateProvider
        .state('home.marketingcommission-creation', {
            templateUrl: 'marketing/marketingcommission-creation.html',
            controller: 'MarketingCommissionCreationCtrl',
            url: '/marketingcommission-creation'
        })
        .state('home.marketingcommission-list', {
            templateUrl: 'marketing/marketingcommission-list.html',
            controller: 'MarketingCommissionListCtrl',
            url: '/marketingcommission-list'
        })
        .state('home.marketingcommission-edit', {
            templateUrl: 'marketing/marketingcommission-edit.html',
            controller: 'MarketingCommissionEditCtrl',
            url: '/marketingcommission-edit/:id'
        })

        .state('home.marketingcommission-detail', {
            templateUrl: 'marketing/marketingcommission-detail.html',
            controller: 'MarketingCommissionDetailCtrl',
            url: '/marketingcommission-detail/:id'
        })

        .state('home.commissiondistribution-list', {
            templateUrl: 'marketing/commissiondistribution-list.html',
            controller: 'MarketingCommissionDistributionCtrl',
            url: '/commissiondistribution-list'
        })
}]);