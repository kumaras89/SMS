'use strict';

angular.module('FMS', ['ngRoute', 'ngCookies']).
    config(['$routeProvider',function ($routeProvider) {
        $routeProvider.when('/fms/:category/:uploaderid', {templateUrl: 'student/student-list.html', controller: 'FMSCtrl'});
    }]);
