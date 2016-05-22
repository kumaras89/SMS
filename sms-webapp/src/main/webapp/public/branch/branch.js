'use strict';

angular.module('Branch', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/branch-list', {templateUrl: 'branch/branch-list.html', controller: 'BranchListCtrl'});
        $routeProvider.when('/branch-detail/:id', {templateUrl: 'branch/branch-detail.html', controller: 'BranchDetailCtrl'});
        $routeProvider.when('/branch-creation', {templateUrl: 'branch/branch-creation.html', controller: 'BranchCreationCtrl'});
    }]);
