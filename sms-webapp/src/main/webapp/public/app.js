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
            'Role',
            'SecuredOperation',
            'RoleOperationLink',
            'Student',
            'MarketingEmployee',
            'FMS'])
        .config(config)
        .run(run);

    config.$inject = ['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider'];
    function config($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {

        localStorage.clear();

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
                controller: 'HomeController',
                templateUrl:'home/home.html',
                resolve: {
                    loadMyFiles:function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'app',
                            files:[
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
            var resource = path.substr(1);
            if(hyphenInedex != -1) {
                resource = path.substr(1, hyphenInedex - 1);
            }
            AuthenticationService.isAuthorized(resource, function() {}, function() {
                    FlashService.Error('Not Authorized to access \"'+ resource+'\"', true);
                    $location.path('/login');
            });

        });
    }

})();