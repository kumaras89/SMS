(function () {
    'use strict';

    angular
        .module('app', ['oc.lazyLoad',
            'ui.router',
            'ui.bootstrap',
            'ngCookies',
            'Branch',
            'User',
            'Course',
            'Scheme',
            'FeesParticular',
            'ScholarshipEnrollment',
            'Role',
            'SecuredOperation',
            'RoleOperationLink',
            'Student',
            'MarketingEmployee',
            'FMS',
            'ScholarshipEnrollment'])
        .config(config)
        .run(run);

    config.$inject = ['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', '$httpProvider'];
    function config($stateProvider,$urlRouterProvider,$ocLazyLoadProvider, $httpProvider) {

        localStorage.clear();

        var interceptor = ['$rootScope', '$q', 'FlashService', function (scope, $q, FlashService) {

            function success(response) {
                return response;
            }

            function error(response) {
                var msg = response.statusText+' on '+ response.config.url +'\n';

                if(response.data) {

                    var msgs = _.pluck(response.data.errorInfo, 'message');
                    _.each(msgs, function(m) {
                        msg += m + ', '
                    })
                    FlashService.Error(msg);
                } else {
                    FlashService.Error(msg);
                }
                return $q.reject(response);
            }

            return function (promise) {
                return promise.then(success, error);
            }

        }];

        $httpProvider.responseInterceptors.push(interceptor);

        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });

        $stateProvider.state('login', {
            templateUrl: 'login/login.view.html',
            controller: 'LoginController',
            controllerAs: 'vm',
            url: '/login'
        })
            .state('home',{
                url:'/home',
                controller: 'HomeController',
                controllerAs: 'vm',
                templateUrl:'home/home.view.html',
                resolve: {
                    loadMyFiles:function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'app',
                            files:[
                                '/public/directives/header/header.js',
                                '/public/directives/header/header-notification/header-notification.js',
                                '/public/directives/sidebar/sidebar.js',
                                '/public/directives/sidebar/sidebar-search/sidebar-search.js',
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
                                '/public/styles/morris.css',
                                '/public/js/moment.js',
                                '/public/js/raphael-min.js',
                                '/public/js/morris.min.js',
                                '/public/directives/notifications/notifications.js',
                                '/public/directives/dashboard/stats/stats.js'
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

        $urlRouterProvider.otherwise('/login');

    }

    run.$inject = ['$rootScope','FlashService','AuthenticationService', '$location', '$cookieStore', '$http'];
    
    function run($rootScope, FlashService, AuthenticationService, $location, $cookieStore, $http) {

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
                    $location.path('/login');
            });

        });
    }

})();