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


            $http.post('/login/changepassword', { userName: vm.username, oldPassword: vm.oldPassword, newPassword : vm.newPassword }).then(function(response) {
                var data = response.data;
                if(data.result) {
                    $location.path('/login');
                } else {
                    failure(data.message)
                }
            }, failure);
        };
        var failure = function(msg){
            FlashService.Error(msg);
            vm.dataLoading = false;
        }
    }

})();
