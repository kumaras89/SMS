(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', '$http' ,'AuthenticationService', 'FlashService', '$rootScope'];
    function LoginController($location, $http, AuthenticationService, FlashService, $rootScope) {
        var vm = this;

        vm.login = login;
        vm.changePassword = changePassword;

        (function initController() {
            AuthenticationService.Logout();
        })();

        function login() {
            vm.dataLoading = true;
            $rootScope.loggedIn = false;
            AuthenticationService.Login(vm.username, vm.password, function () {
                $location.path('/home/');
                $rootScope.loggedIn = true;
            }, function () {
                vm.dataLoading = false;
            });
        };

        function changePassword() {
            vm.dataLoading = true;
            var passwordInput = {
                userName: vm.username,
                oldPassword: vm.oldPassword,
                newPassword : vm.newPassword,
                reTypeNewPwd :  vm.reTypeNewPassword
            }
            $http.post('/login/changepassword', passwordInput)
                .then(function(res) {
                    if(res.data.error) {
                        errorFunc(res)
                    } else {
                        FlashService.Success('Password Succesfully Changed!!', true);
                        $location.path('/login');
                    }
                }, function () {
                    vm.dataLoading = false;
                });
        }
    }

})();
