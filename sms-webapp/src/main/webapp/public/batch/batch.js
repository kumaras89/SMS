/**
 * Created by Assaycr-04 on 8/20/2016.
 */
'use strict';

angular.module('Batch', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });

    $stateProvider
        .state('home.batch-list', {
            templateUrl: 'batch/batch-list.html',
            controller: 'BatchListCtrl',
            url: '/batch-list'
        })
        .state('home.batch-update', {
            templateUrl: 'batch/batch-update.html',
            controller: 'BatchUpdateCtrl',
            url: '/batch-update/:id'
        })
        .state('home.batch-creation', {
            templateUrl: 'batch/batch-creation.html',
            controller: 'BatchCreationCtrl',
            url: '/batch-creation'
        })
}]);
