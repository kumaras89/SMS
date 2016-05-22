'use strict';

angular.module('User', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/user-list', {templateUrl: 'user/user-list.html', controller: 'UserListCtrl'});
        $routeProvider.when('/user-detail/:id', {templateUrl: 'user/user-detail.html', controller: 'UserDetailCtrl'});
        $routeProvider.when('/user-creation', {templateUrl: 'user/user-creation.html', controller: 'UserCreationCtrl'});
    }]);
