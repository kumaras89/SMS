(function () {
    'use strict';

    angular
        .module('app', ['oc.lazyLoad',
            'ui.router',
            'ui.bootstrap',
            'ngCookies',
            'ngSanitize','ngMessages',
            'Branch',
            'User',
            'Course',
            'Batch',
            'Scheme',
            'FeesParticular',
            'ScholarshipEnrollment',
            'Role',
            'SecuredOperation',
            'RoleOperationLink',
            'Student',
            'MarketingEmployee',
            'FMS',
            'ScholarshipEnrollment',
            'IDCard',
            'Payment',
            'Hotel',
            'HotelHr',
            'HotelTracker',
            'angular-confirm',
            'MessageService',
            'Attendance',
            'Expense',
            'MarketingCommission'])
           .factory('errorInterceptor',['$rootScope', '$q', 'FlashService', function (scope, $q, FlashService) {
                return {
                    responseError: function (response) {
                        window.scrollTo(0,0);
                        var standardMsg = response.statusText+' on '+ response.config.url;
                        if(response.data) {
                            var msg = ''
                            var msgs = _.pluck(response.data.errorInfo, 'message');
                            _.each(msgs, function(m) {
                                msg += m + '<br>'
                            })
                            if(msg === '') {
                                msg = standardMsg;
                            }
                            FlashService.Error(msg);
                        } else {
                            FlashService.Error(standardMsg);
                        }
                        return $q.reject(response);
                    }
                };
         }])
        .config(config)
        .run(run)

        .directive("userName", ['$rootScope','AdminService','$timeout', function ($rootScope, AdminService, $timeout) {
            return {
                restrict: 'A',
                scope :{
                    us : '=us',
                    fn : '=fn'
                },
                controller: function($scope){
                    var logInUserDet = $rootScope.globals.currentUser.otherDetails;
                    if(logInUserDet.role != 'SUPER_ADMIN'){
                        var name = $rootScope.globals.currentUser.username;
                        $timeout(function () {
                            $scope.$apply(function () {
                                $scope.us = name;
                            })
                        });
                    }
                    AdminService.getUsers(function (data) {
                        $scope.userNames = _.pluck(data,"name");
                    })
                },
                template: function(){
                    if($rootScope.globals.currentUser.otherDetails.role === 'SUPER_ADMIN'){
                        return' <input type="text" ng-model="us" uib-typeahead="uName for uName in userNames | filter:$viewValue | limitTo:8" id="userName" placeholder="User" autocomplete="off"/>'
                    }else{
                        return '<label class="control-label">{{us}}</label>'
                    }
                }
            }
        }])
        .directive("branchName", ['$rootScope','AdminService','$timeout', function ($rootScope, AdminService, $timeout) {
            return {
                restrict: 'A',
                scope :{
                    bn : '=bn',
                    fn: "=fn"
                },
                controller: function($scope){
                    if($rootScope.globals.currentUser.otherDetails.role != 'SUPER_ADMIN'){
                        var name = AdminService.getBranchDesc($rootScope.globals.currentUser.otherDetails.branch);
                        $timeout(function () {
                            $scope.$apply(function () {
                                $scope.bn = name;
                            })
                        });
                    }

                    AdminService.getBranches(function (data) {
                        $scope.branches = data;
                        $scope.branchNames = _.pluck(data, "name");
                    });
                },
                template: function(){
                    if($rootScope.globals.currentUser.otherDetails.role === 'SUPER_ADMIN'){
                        return '<input type="text" name="bName" ng-model="bn" uib-typeahead="bName for bName in branchNames | filter:$viewValue ' +
                                       '| limitTo:8" id="branchName" style="width: 42%;" placeholder="Branch" autocomplete="off" required/>'+
                                        '<span class="error" ng-messages="fn.bName.$error" ng-if="fn.branchName.$touched">'+
                                           ' <span ng-message="required">Branch is required.</span>'+
                                        '</span>'
                    }else{
                        return '<label class="control-label">{{bn}}</label>'
                    }
                }
            }
        }])
        .directive("addressTemplate",function(){
            return{
                restrict: 'A',
                scope:{
                    data:"=",
                    addfrm:"="
                },
                templateUrl : 'common/address-template.html'
            }
        });

    config.$inject = ['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', '$httpProvider'];
    function config($stateProvider,$urlRouterProvider,$ocLazyLoadProvider, $httpProvider) {

        localStorage.clear();
        $httpProvider.interceptors.push('errorInterceptor');

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider.state('login', {
            templateUrl: 'login/login.view.html',
            controller: 'LoginController',
            controllerAs: 'vm',
            url: '/login'
        }).state('changepassword', {
            templateUrl: 'login/login-changepassword.view.html',
            controller: 'LoginController',
            controllerAs: 'vm',
            url: '/changepassword'
        }).state('home',{
                url:'/home',
                controller: 'HomeController',
                controllerAs: 'vm',
                templateUrl:'home/home.view.html',
                resolve: {
                    loadMyFiles:function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'app',
                            files:[
                                '/public/app-services/directives/header/header.js',
                                '/public/app-services/directives/header/header-notification/header-notification.js',
                                '/public/app-services/directives/sidebar/sidebar.js',
                                '/public/app-services/directives/sidebar/sidebar-search/sidebar-search.js'
                            ]
                        })
                    }
                }
            })
            .state('home.dashboard',{
                url:'/',
                controller: 'DashBoardCtrl',
                templateUrl:'home/home.html',
                resolve: {
                    loadMyFiles:function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'app',
                            files:[
                                '/public/app-content/styles/morris.css',
                                '/public/app-content/js/moment.js',
                                '/public/app-content/js/raphael-min.js',
                                '/public/app-content/js/morris.min.js',
                                '/public/app-services/directives/notifications/notifications.js',
                                '/public/app-services/directives/dashboard/stats/stats.js'
                            ]
                        })
                    }
                }
            })
            .state('login-changepassword', {
                templateUrl: 'login/login-changepassword.view.html',
                controller: 'LoginController',
                controllerAs: 'vm',
                url: '/login'
            })
            .state('logout', {
                templateUrl: 'login/login.view.html',
                controller: 'LoginController',
                controllerAs: 'vm',
                url: '/login'
            })
            .state('home.student-dashboard', {
                templateUrl: 'home/student-dashboard.html',
                controller: 'StudentDashboardCtrl',
                url: '/student-dashboard'
            })
            .state('home.income-dashboard', {
                templateUrl: 'home/income-dashboard.html',
                controller: 'IncomeDashboardCtrl',
                url: '/income-dashboard'
            })
            .state('home.expense-dashboard', {
                templateUrl: 'home/expense-dashboard.html',
                controller: 'ExpenseDashboardCtrl',
                url: '/expense-dashboard'
            })
            .state('home.scholarship-dashboard', {
                templateUrl: 'home/scholarship-dashboard.html',
                controller: 'ScholarDashboardCtrl',
                url: '/scholarship-dashboard'
            })


        $urlRouterProvider.otherwise('/login');

    }

    run.$inject = ['$rootScope','FlashService','AuthenticationService', '$location', '$cookieStore', '$http', 'AdminServiceProvider', '$state'];
    
    function run($rootScope, FlashService, AuthenticationService, $location, $cookieStore, $http, AdminServiceProvider, $state) {

        $rootScope.adminService = function() {
            return AdminServiceProvider.initService();
        }

        $rootScope.goBack = function() {
            $state.go($rootScope.previousState.stateName, $rootScope.previousState.stateParams)
        }

        $rootScope.greaterThan = function(prop, val){
            return function(item){
                return item[prop] > val;
            }
        }
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        var lastDigestRun = new Date();
        //logout if user inactive for in 10 mins
        $rootScope.$watch(function detectIdle() {
            var now = new Date();
            if (now - lastDigestRun > 10*60*60) {
                console.log('log out automatically');
                // AuthenticationService.Logout();
            }
            lastDigestRun = now;
        });

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page

            var path  = $location.path();
            var hyphenInedex = path.indexOf('-');
            var resource = path.substr(6);
            if(hyphenInedex != -1) {
                resource = path.substr(6, hyphenInedex - 6);
            }
            AuthenticationService.isAuthorized(resource, function() {}, function() {
                FlashService.Error('Not Authorized to access \"'+ resource+'\"', true);
                AuthenticationService.Logout()
            });
        });

        $rootScope.previousState;
        $rootScope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {
            $rootScope.previousState = {
                stateName : from.name,
                stateParams : fromParams
            }
        });
    }

})();