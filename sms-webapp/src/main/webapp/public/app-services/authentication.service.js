(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService)
        .directive('includeIfAllowed', function(AuthenticationService){
            return {
                restrict: 'A',
                priority: 100000,
                scope: false,
                link: function(){
                    // alert('ergo sum!');
                },
                compile:  function(element, attr, linker){
                    AuthenticationService.isAuthorized(attr.operation, function(){}, function() {
                        element.children().remove();
                        element.remove();
                    });
                }
            }
        });

    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope', 'StorageService'];
    function AuthenticationService($http, $cookieStore, $rootScope, StorageService) {
        var service = {};

        service.Login = Login;
        service.Logout = Logout;
        service.isAuthorized = isAuthorized;

        return service;

        function Logout() {
            $rootScope.loggedIn = false;
            $http.post('/logout').then(function(response) {
                if(response.data.result) {
                    ClearCredentials()
                }
            });
        }

        function Login(username, password, success, failure) {

            $http.post('/authenticate', { username: username, password: password }).then(function(response) {
                var data = response.data;
                if(data.result) {
                    SetCredentials(username, password, {
                        name : data.user.firstName + ' ' + data.user.lastName,
                        role : data.user.role,
                        branch: data.user.branch,
                        marketingEmployee : data.user.markettingEmployee,
                        allowedOperations : data.user.allowedOperations
                    });
                    success(data);
                } else {
                    failure(data.message)
                }
            }, failure);

        }

        function isAuthorizedWithLocalStorage(resource, success, failure, securedOperations) {
            if(resource == 'login' || !_.contains(securedOperations, resource)){
                success()
            } else {
                var currentUser = $rootScope.globals.currentUser;
                if(currentUser) {
                    if(_.contains(currentUser.otherDetails.allowedOperations, resource)){
                        success()
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }
        }


        function isAuthorized(resource, success, failure) {
            StorageService.getFromStoarage('/login/securedoperation', function(securedOperation) {
                isAuthorizedWithLocalStorage(resource, success, failure, securedOperation)
            });
        }
        
        function SetCredentials(username, password, otherDetails) {
            var authdata = Base64.encode(username + ':' + password);
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata,
                    otherDetails: otherDetails
                }
            };
            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }

    // Base64 encoding service used by AuthenticationService
    var Base64 = {

        keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',

        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    this.keyStr.charAt(enc1) +
                    this.keyStr.charAt(enc2) +
                    this.keyStr.charAt(enc3) +
                    this.keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = this.keyStr.indexOf(input.charAt(i++));
                enc2 = this.keyStr.indexOf(input.charAt(i++));
                enc3 = this.keyStr.indexOf(input.charAt(i++));
                enc4 = this.keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };

})();