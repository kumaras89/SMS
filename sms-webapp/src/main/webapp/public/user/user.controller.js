(function () {
    'use strict';

    angular
        .module('User')
        .controller('UserListCtrl', ['$scope', 'CrudService','AdminService', 'FlashService', '$location','$http', 'ngTableParams', '$timeout','$uibModal',
            function ($scope, CrudService,AdminService, FlashService, $location,$http, ngTableParams, $timeout, $uibModal) {

                $scope.editUser = function (userId) {
                    $location.path('/home/user-detail/' + userId);
                };

                $scope.deleteUser = function(id) {
                    CrudService.userService.Delete(id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Deleted !!", false);
                        $scope.tableParams.reload();
                    });
                };

                $scope.resetPassword = function(id) {
                    $http.put('/user/resetpassword/' + id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success('Password Resetted!!')
                    }, function (res) {
                        FlashService.Error(res.message)
                    });
                }

                $scope.createNewUser = function () {
                    $location.path('/home/user-creation');
                };

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function ($defer, params) {
                        CrudService.userService.GetAll().then(function (res) {
                            if (res.message) {
                                $scope.entities = []
                                FlashService.Error(res.message)
                            } else {
                                $scope.entities = res;
                            }
                            $timeout(function() {
                                params.total($scope.entities.length);
                                $defer.resolve($scope.entities);
                            }, 10);
                        }, function() {
                            $scope.entities = []
                            $timeout(function() {
                                params.total($scope.entities.length);
                                $defer.resolve($scope.entities);
                            }, 10);
                        })
                    }
                });

                AdminService.getBranches(function(data) {
                    $scope.branches = data;
                })

                $scope.getBranchDesc = AdminService.getBranchDesc


            }])
        .controller('UserDetailCtrl', ['$scope', '$stateParams', 'AdminService', 'CrudService','FlashService', '$location',
            function ($scope, $stateParams,AdminService, CrudService,FlashService, $location) {

                $scope.updateUser = function () {
                    var user = {};
                    var user = $.extend(user, $scope.user);
                    user.branch = AdminService.getBranchCode($scope.user.branch);
                    CrudService.userService.Update(user).then(function(){
                        window.scrollTo(0,0);
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
                    user.name= $scope.user.name.toUpperCase(); //fixed for duplicating the username with case sensitive.
                    user.branch = AdminService.getBranchCode($scope.user.branch);
                    CrudService.userService.Create(user).then(function (res) {
                            window.scrollTo(0,0);
                            if(res.message) {
                                FlashService.Error(res.message);
                            } else {
                                FlashService.Success("Successfuly Inserted !!", true);
                                $location.path('/home/user-list');
                            }
                        }
                        /*, function(res) {
                         FlashService.Error(res.message);
                         }*/);
                }

                $scope.init = function() {
                    AdminService.getRoles(function(data) {
                        $scope.roles = data;
                        $scope.roles = _.pluck(data, "name");
                    })
                   /* Abhijit- already defined in the branchName directive in app.js */
                  /*  AdminService.getBranches(function(data) {
                        $scope.branches = data;
                        $scope.branchNames = _.pluck(data,"name")
                    })*/
                    
                }

                $scope.init();
            }]);


})();
