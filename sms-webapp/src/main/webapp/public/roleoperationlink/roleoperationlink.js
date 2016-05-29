'use strict';

angular.module('RoleOperationLink', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/roleoperationlink-list', {templateUrl: 'roleoperationlink/roleoperationlink-list.html', controller: 'RoleOperationLinkCtrl'});
    }]);
