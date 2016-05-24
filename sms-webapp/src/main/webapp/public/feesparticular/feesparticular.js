'use strict';

angular.module('FeesParticular', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/feesparticular-list', {templateUrl: 'feesparticular/feesparticular-list.html', controller: 'FeesParticularListCtrl'});
        $routeProvider.when('/feesparticular-detail/:id', {templateUrl: 'feesparticular/feesparticular-detail.html', controller: 'FeesParticularDetailCtrl'});
        $routeProvider.when('/feesparticular-creation', {templateUrl: 'feesparticular/feesparticular-creation.html', controller: 'FeesParticularCreationCtrl'});
    }]);
