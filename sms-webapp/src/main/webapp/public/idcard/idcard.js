'use strict';

angular.module('IDCard', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.idcard-search', {
                templateUrl: 'idcard/idcard-search.html',
                controller: 'IDCardListCtrl',
                url: '/idcard-search'
            })
            .state('home.idcard-detail', {
                templateUrl: 'idcard/idcard-detail.html',
                controller: 'IDCardDetailCtrl',
                url: '/idcard-detail/:id'
            })
    }]);
