'use strict';

angular.module('Course', ['ngRoute', 'ngCookies']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/course-list', {templateUrl: 'course/course-list.html', controller: 'CourseListCtrl'});
        $routeProvider.when('/course-detail/:id', {templateUrl: 'course/course-detail.html', controller: 'CourseDetailCtrl'});
        $routeProvider.when('/course-creation', {templateUrl: 'course/course-creation.html', controller: 'CourseCreationCtrl'});
    }]);
