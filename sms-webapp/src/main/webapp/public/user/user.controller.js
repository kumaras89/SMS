(function () {
    'use strict';

    angular
        .module('User')
        .controller('UserListCtrl', ['$scope', 'CrudService', 'FlashService', '$location','$http',
        function ($scope, CrudService, FlashService, $location,$http) {
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
                $http.put('/user/resetpassword/' + id).then(function(){
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
        .controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserService', 'CrudService','FlashService', '$location',
        function ($scope, $routeParams,UserService, CrudService,FlashService, $location) {

            $scope.updateUser = function () {
                CrudService.userService.Update($scope.user).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/user-list');
                });

            };

            $scope.loadUser = function() {
                CrudService.userService.GetById($routeParams.id).then(function(res) {
                    $scope.user = res
                })
                UserService.getRoles(function(data) {
                    $scope.roles = data;
                })
                UserService.getBranches(function(data) {
                    $scope.branches = data;
                    $scope.branchNames = _.pluck(data,"name")
                })
            }

            $scope.loadUser();
        }])
        .controller('UserCreationCtrl', ['$scope', 'CrudService','UserService','FlashService', '$location',
        function ($scope, CrudService,UserService,FlashService, $location) {

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

            $scope.init = function() {
                UserService.getRoles(function(data) {
                    $scope.roles = data;
                })
                UserService.getBranches(function(data) {
                    $scope.branches = data;
                    $scope.branchNames = _.pluck(data,"name")
                })
            }

            $scope.init();
        }]);

})();
