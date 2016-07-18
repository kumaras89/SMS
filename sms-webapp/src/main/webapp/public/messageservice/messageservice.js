'use strict';

angular.module('MessageService', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });
    $stateProvider
        .state('home.messageservice-list', {
            templateUrl: 'messageservice/messageservice-list.html',
            controller: 'MessageServiceListCtrl',
            url: '/messageservice-list'
        })
        .state('home.messageservice-creation', {
            templateUrl: 'messageservice/messageservice-creation.html',
            controller: 'MessageServiceCreationCtrl',
            url: '/messageservice-creation'
        })
}]);
