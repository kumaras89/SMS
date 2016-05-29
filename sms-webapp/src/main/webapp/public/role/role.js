'use strict';

angular.module('Role', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/role-list', {templateUrl: 'role/role-list.html', controller: 'CrudCtrl'});
        $routeProvider.when('/role-detail/:id', {templateUrl: 'role/role-detail.html', controller: 'CrudCtrl'});
        $routeProvider.when('/role-creation', {templateUrl: 'role/role-creation.html', controller: 'CrudCtrl'});
    }]);
