(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashBoardCtrl', ['$scope', 'CrudService', 'FlashService', '$location', 'AdminService', '$cookieStore',
            function ($scope, CrudService, FlashService, $location, AdminService, $cookieStore) {

                $scope.loggedInBranchCode = $cookieStore.get('globals').currentUser.otherDetails.branch;

                $scope.getBranchDesc = function (branchCode) {
                    return AdminService.getBranchDesc(branchCode);
                };

                $scope.getCourseDesc = function (courseCode) {
                    return AdminService.getCourseDesc(courseCode);
                };

                $scope.getSchemeDesc = function (schemeCode) {
                    return AdminService.getSchemeDesc(schemeCode);
                };

                $scope.loadStudents = function () {
                    CrudService.studentService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.students = []
                            FlashService.Error(res.message)
                        } else {
                            $scope.students = res
                        }
                    }, function () {
                        $scope.students = []
                    })
                }

                $scope.loadStudents()
            }]);

})();
