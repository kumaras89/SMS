(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', '$http' ,'AuthenticationService', 'FlashService'];
    function LoginController($location, $http, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;
        vm.changePassword = changePassword;

        (function initController() {
            AuthenticationService.Logout();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function () {
                $location.path('/home');
            }, failure);
        };

        function changePassword() {
            vm.dataLoading = true;
            var passwordInput = {
                userName: vm.username,
                oldPassword: vm.oldPassword,
                newPassword : vm.newPassword,
                reTypeNewPwd :  vm.reTypeNewPassword
            };
            var errorFunc = function(res) {
                if (typeof res.data.error === 'string') {
                    failure(res.data.error)
                } else {
                    failure(_.values(res.data.error).join('\n'))
                }

            }
            $http.post('/login/changepassword', passwordInput)
                .then(function(res) {
                    if(res.data.error) {
                        errorFunc(res)
                    } else {
                        FlashService.Success('Password Succesfully Changed!!', true);
                        $location.path('/login');
                    }
                },errorFunc);
        };
        var failure = function(msg){
            FlashService.Error(msg);
            vm.dataLoading = false;
        }
    }

})();
