'use strict';

angular.module('Payment', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider',
    function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.payment-detail', {
                templateUrl: 'payment/payment-detail.html',
                controller: 'PaymentCtrl',
                url: '/payment-detail/:studentid'
            })
            .state('home.payment-creation', {
                templateUrl: 'payment/payment-creation.html',
                controller: 'PaymentCtrl',
                url: '/payment-creation/:studentid'
            })
            .state('home.payment-list', {
                templateUrl: 'payment/payment-list.html',
                controller: 'PaymentListCtrl',
                url: '/payment-list/:branch'
            })
    }]);
