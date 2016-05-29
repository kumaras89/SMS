'use strict';

angular.module('SecuredOperation', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/securedoperation-list', {templateUrl: 'securedoperation/securedoperation-list.html', controller: 'CrudCtrl'});
        $routeProvider.when('/securedoperation-detail/:id', {templateUrl: 'securedoperation/securedoperation-detail.html', controller: 'CrudCtrl'});
        $routeProvider.when('/securedoperation-creation', {templateUrl: 'securedoperation/securedoperation-creation.html', controller: 'CrudCtrl'});
    }]);
