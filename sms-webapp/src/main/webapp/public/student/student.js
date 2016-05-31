'use strict';

angular.module('Student', ['ngRoute', 'ngCookies']).
    config(['$routeProvider',function ($routeProvider) {
        $routeProvider.when('/student-list', {templateUrl: 'student/student-list.html', controller: 'StudentListCtrl'});
        $routeProvider.when('/student-detail/:id', {templateUrl: 'student/student-detail.html', controller: 'StudentDetailCtrl'});
        $routeProvider.when('/student-creation', {templateUrl: 'student/student-creation.html', controller: 'StudentCreationCtrl'});
    }]);
