(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'Branch', 'User', 'Course', 'Scheme', 'FeesParticular', 'Role', 'SecuredOperation', 'RoleOperationLink', 'Student', 'MarketingEmployee', 'FMS'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider'];
    function config($routeProvider) {
        localStorage.clear();
        $routeProvider.when('/home', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            }).when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            }).when('/login-changepassword', {
                controller: 'LoginController',
                templateUrl: 'login/login-changepassword.view.html',
                controllerAs: 'vm'
            }).when('/logout', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            }).otherwise({ redirectTo: '/login' });
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