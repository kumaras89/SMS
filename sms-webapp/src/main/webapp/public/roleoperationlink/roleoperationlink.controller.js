(function () {
    'use strict';

    angular
        .module('RoleOperationLink')
        .controller('RoleOperationLinkCtrl', ['$rootScope', '$scope', '$http','CrudService', 'FlashService',
            function ($rootScope, $scope, $http, CrudService, FlashService) {

                $scope.save = function(id) {
                    $scope.rol.userRoleId = id;
                    $http.post('/roleoperationlink', $scope.rol).then(function() {
                        FlashService.Success('Successfully Modified!!');
                        $rootScope.globals.currentUser.otherDetails.allowedOperations =
                            _.pluck(
                                _.filter($scope.securedOperations,
                                    function(so) {
                                        return _.some($scope.rol.linkedOperations,
                                            function(lo) { return so.id === lo; }) }), "operation");
                    })
                };

                $scope.isChecked = function(id, roleId) {
                    $scope.roleoperationlinksOfUser = _.find($scope.roleoperationlinks, function(rol) {
                        return rol.userRoleId = roleId;
                    });
                    $scope.linkedOperations = $scope.roleoperationlinksOfUser.linkedOperations;
                    return $scope.linkedOperations.indexOf(id) > -1;
                };

                $scope.updateLinkedOperations = function (roleId) {
                    $scope.roleoperationlinksOfUser = _.find($scope.roleoperationlinks, function(rol) {
                        return rol.userRoleId = roleId;
                    });
                    $scope.rol.linkedOperations = $scope.roleoperationlinksOfUser.linkedOperations;
                };

                $scope.toggleSelection = function toggleSelection(operationId) {
                    var idx = $scope.rol.linkedOperations.indexOf(operationId);
                    if (idx > -1) {
                        $scope.rol.linkedOperations.splice(idx, 1);
                    } else {
                        $scope.rol.linkedOperations.push(operationId);
                    }
                };

                $scope.init = function () {
                    CrudService.of('securedoperation').GetAll().then(function(res) {
                        $scope.securedOperations = res;
                    });
                    CrudService.of('role').GetAll().then(function(res) {
                        $scope.roles = res;
                    });
                    $http.get('/roleoperationlink').then(function(res) {
                        if(res.data) {
                            $scope.roleoperationlinks = res.data;
                        }
                    });
                    $scope.rol = {};
                    $scope.rol.linkedOperations = [];
                }
            }]);

})();
