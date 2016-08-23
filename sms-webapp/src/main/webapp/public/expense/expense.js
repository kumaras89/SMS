'use strict';

angular.module('Expense', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable']).
config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

    $ocLazyLoadProvider.config({
        debug: false,
        events: true
    });

    $stateProvider
        .state('home.expense-list', {
            templateUrl: 'expense/expense-list.html',
            controller: 'ExpenseListCtrl',
            url: '/expense-list'
        })
        .state('home.expense-detail', {
            templateUrl: 'expense/expense-detail.html',
            controller: 'ExpenseDetailCtrl',
            url: '/expense-detail/:id'
        })
        .state('home.expense-creation', {
            templateUrl: 'expense/expense-creation.html',
            controller: 'ExpenseCreationCtrl',
            url: '/expense-creation'
        })
        .state('home.expense-edit', {
            templateUrl: 'expense/expense-edit.html',
            controller: 'ExpenseEditCtrl',
            url: '/expense-edit/:id'
        })
}]);
