'use strict';

angular.module('RoleOperationLink', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies']).
    config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.roleoperationlink-list', {
                templateUrl: 'roleoperationlink/roleoperationlink-list.html',
                controller: 'RoleOperationLinkCtrl',
                url: '/roleoperationlink-list'
            })
    }]);
