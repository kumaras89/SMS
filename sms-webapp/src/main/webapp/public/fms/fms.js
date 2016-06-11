'use strict';

angular.module('FMS', ['ngRoute', 'ngCookies']).
    config(['$routeProvider',function ($routeProvider) {
        $routeProvider.when('/fms/:category/:uploaderid', {templateUrl: 'fms/fms-main.html', controller: 'FMSCtrl'});
    }]).directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                scope.$apply(function () {
                    scope.fileread = changeEvent.target.files[0];
                    // or all selected files:
                    // scope.fileread = changeEvent.target.files;
                });
            });
        }
    }
}]);
