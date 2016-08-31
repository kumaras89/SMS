'use strict';

angular.module('ScholarshipEnrollment', ['oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'ngTable'])
    .directive("marketingEmployee", ['$rootScope','AdminService','$timeout', function ($rootScope, AdminService, $timeout) {
        return {
            restrict: 'A',
            scope :{
                mn : '=mn'
            },
            controller: function($scope){
                var logInUserDet = $rootScope.globals.currentUser.otherDetails;
                if(logInUserDet.role != 'SUPER_ADMIN'){
                    var name = AdminService.getMarketingEmployeeName(logInUserDet.marketingEmployee);
                    $timeout(function () {
                        $scope.$apply(function () {
                            $scope.mn = name;
                        })
                    });
                }

                AdminService.getMarketingEmployees(function (data) {
                    $scope.marketingEmployeeNames = _.pluck(data, "name");
                });
            },
            template: function(){
                if($rootScope.globals.currentUser.otherDetails.role === 'SUPER_ADMIN'){
                    return '<input type="text" ng-model="mn" uib-typeahead="mName for mName in marketingEmployeeNames | filter:$viewValue | limitTo:8" id="markettingEmployeeName" placeholder="Marketting Employee Name" autocomplete="off" />'
                }else{
                    return '<label class="control-label">{{mn}}</label>'
                }
            }
        }
    }]).config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider
            .state('home.scholarshipenrollment-list', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-list.html',
                controller: 'ScholarshipEnrollmentListCtrl',
                url: '/scholarshipenrollment-list'
            })
            .state('home.scholarshipenrollment-detail', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-detail.html',
                controller: 'ScholarshipEnrollmentDetailCtrl',
                url: '/scholarshipenrollment-detail/:id'
            })
            .state('home.scholarshipenrollment-edit', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-edit.html',
                controller: 'ScholarshipEnrollmentEditCtrl',
                url: '/scholarshipenrollment-edit/:id'
            })
            .state('home.scholarshipenrollment-creation', {
                templateUrl: 'scholarshipenrollment/scholarshipenrollment-creation.html',
                controller: 'ScholarshipEnrollmentCreationCtrl',
                url: '/scholarshipenrollment-creation'
            })
    }]);
