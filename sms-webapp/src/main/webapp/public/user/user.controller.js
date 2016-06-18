(function () {
    'use strict';

    angular
        .module('User')
        .controller('UserListCtrl', ['$scope', 'CrudService','AdminService', 'FlashService', '$location','$http',
        function ($scope, CrudService,AdminService, FlashService, $location,$http) {
            $scope.editUser = function (userId) {
                $location.path('/home/user-detail/' + userId);
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
                $location.path('/home/user-creation');
            };

            $scope.loadUsers = function () {
                var loadUserFunc = function() {
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
                };

                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                    loadUserFunc()
                })

            }

            $scope.getBranchDesc = AdminService.getBranchDesc

            $scope.loadUsers()



        }])
        .controller('UserDetailCtrl', ['$scope', '$stateParams', 'AdminService', 'CrudService','FlashService', '$location',
        function ($scope, $stateParams,AdminService, CrudService,FlashService, $location) {

            $scope.updateUser = function () {
                var user = {};
                var user = $.extend(user, $scope.user);
                user.branch = AdminService.getBranchCode($scope.user.branch);
                CrudService.userService.Update(user).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/home/user-list');
                });

            };

            $scope.loadUser = function() {
                var loadUserFunc = function(){
                    CrudService.userService.GetById($stateParams.id).then(function(res) {
                        var user = res;
                        user.branch = AdminService.getBranchDesc(res.branch);
                        $scope.user = user;
                    })
                }
                AdminService.getRoles(function(data) {
                    $scope.roles = _.pluck(data,"name");
                })
                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                    $scope.branchNames = _.pluck(data,"name")
                    loadUserFunc();
                })
            }

            $scope.loadUser();
        }])
        .controller('UserCreationCtrl', ['$scope', 'CrudService','AdminService','FlashService', '$location',
        function ($scope, CrudService,AdminService,FlashService, $location) {

            $scope.createNewUser = function () {
                var user = {};
                var user = $.extend(user, $scope.user);
                user.branch = AdminService.getBranchCode($scope.user.branch);
                CrudService.userService.Create(user).then(function (res) {
                    if(res.message) {
                        FlashService.Error(res.message);
                    } else {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $location.path('/home/user-list');
                    }
                }, function(res) {
                    FlashService.Error(res.message);
                });
            }

            $scope.init = function() {
                AdminService.getRoles(function(data) {
                    $scope.roles = _.pluck(data, "name");
                })
                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                    $scope.branchNames = _.pluck(data,"name")
                })
            }

            $scope.init();
        }]);

})();
