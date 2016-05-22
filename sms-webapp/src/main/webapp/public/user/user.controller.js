(function () {
    'use strict';

    angular
        .module('User')
        .controller('UserListCtrl', ['$scope', 'CrudService', 'FlashService', '$location',
        function ($scope, CrudService, FlashService, $location) {
            $scope.editUser = function (userId) {
                $location.path('/user-detail/' + userId);
            };

            $scope.deleteUser = function (id) {
                CrudService.userService.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.loadUsers();
                });

            };

            $scope.resetPassword = function(id) {
                $http.put('/resetpassword/' + data.id, data).then(function(){
                    FlashService.Success('Password Resetted!!')
                }, function (res) {
                    FlashService.Error(res.message)
                });
            }

            $scope.createNewUser = function () {
                $location.path('/user-creation');
            };

            $scope.loadUsers = function () {
                CrudService.userService.GetAll().then(function(res) {
                    if(res.message) {
                        $scope.users = []
                        FlashService.Error(res.message)
                    } else {
                        $scope.users = res
                    }
                }, function() {
                    $scope.users = []
                })
            }

            $scope.loadUsers()



        }])
        .controller('UserDetailCtrl', ['$scope', '$routeParams', 'CrudService','FlashService', '$location',
        function ($scope, $routeParams, CrudService,FlashService, $location) {

            $scope.updateUser = function () {
                CrudService.userService.Update($scope.user).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/user-list');
                });

            };

            $scope.cancel = function () {
                $location.path('/user-list');
            };

            $scope.loadUser = function() {
                CrudService.userService.GetById($routeParams.id).then(function(res) {
                    $scope.user = res
                })
            }

            $scope.loadUser();
        }])
        .controller('UserCreationCtrl', ['$scope', 'CrudService','FlashService', '$location',
        function ($scope, CrudService,FlashService, $location) {

            $scope.createNewUser = function () {
                CrudService.userService.Create($scope.user).then(function (res) {
                    if(res.message) {
                        FlashService.Error(res.message);
                    } else {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $location.path('/user-list');
                    }
                }, function(res) {
                    FlashService.Error(res.message);
                });

            }
        }]);

})();
