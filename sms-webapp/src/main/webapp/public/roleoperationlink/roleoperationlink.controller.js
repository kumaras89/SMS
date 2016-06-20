(function () {
    'use strict';

    angular
        .module('RoleOperationLink')
        .controller('RoleOperationLinkCtrl', ['$rootScope', '$scope', '$http','CrudService', 'FlashService',
            function ($rootScope, $scope, $http, CrudService, FlashService) {

                $scope.save = function() {
                    $http.post('/roleoperationlink', $scope.rol).then(function() {
                        FlashService.Success('Successfully Modified!!');
                        $scope.loadRol();
                    })
                }

                $scope.isChecked = function(id) {
                    return $scope.rol.linkedOperations.indexOf(id) > -1;
                }

                $scope.userRoleChanged = function() {
                    var linkedOperationOfUserRole = _.find($scope.roleoperationlinks, function(rol) {
                        return rol.userRoleId == $scope.rol.userRoleId;
                    })
                    if(linkedOperationOfUserRole) {
                        $scope.rol.linkedOperations = linkedOperationOfUserRole.linkedOperations;
                    } else {
                        $scope.rol.linkedOperations = [];
                    }
                }

                $scope.toggleSelection = function toggleSelection(operationId) {
                    var idx = $scope.rol.linkedOperations.indexOf(operationId);
                    if (idx > -1) {
                        $scope.rol.linkedOperations.splice(idx, 1);
                    } else {
                        $scope.rol.linkedOperations.push(operationId);
                    }
                };

                $scope.loadRol = function() {
                    $http.get('/roleoperationlink').then(function(res) {
                        if(res.data) {
                            $scope.roleoperationlinks = res.data;
                        }
                    })
                }

                $scope.init = function () {
                    CrudService.of('securedoperation').GetAll().then(function(res) {
                        $scope.securedOperations = res;
                    })
                    CrudService.of('role').GetAll().then(function(res) {
                        $scope.roles = res;
                    })
                    $scope.loadRol();
                    $scope.rol = {}
                    $scope.rol.linkedOperations = [];
                }

            }]);

})();
