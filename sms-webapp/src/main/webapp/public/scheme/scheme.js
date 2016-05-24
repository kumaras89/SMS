'use strict';

angular.module('Scheme', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/scheme-list', {templateUrl: 'scheme/scheme-list.html', controller: 'SchemeListCtrl'});
        $routeProvider.when('/scheme-detail/:id', {templateUrl: 'scheme/scheme-detail.html', controller: 'SchemeDetailCtrl'});
        $routeProvider.when('/scheme-creation', {templateUrl: 'scheme/scheme-creation.html', controller: 'SchemeCreationCtrl'});
    }]);
