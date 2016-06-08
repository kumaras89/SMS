'use strict';

angular.module('MarketingEmployee', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/marketingemployee-list', {templateUrl: 'marketingemployee/marketingemployee-list.html', controller: 'MarketingEmployeeListCtrl'});
        $routeProvider.when('/marketingemployee-detail/:id', {templateUrl: 'marketingemployee/marketingemployee-detail.html', controller: 'MarketingEmployeeDetailCtrl'});
        $routeProvider.when('/marketingemployee-creation', {templateUrl: 'marketingemployee/marketingemployee-creation.html', controller: 'MarketingEmployeeCreationCtrl'});
    }]);
