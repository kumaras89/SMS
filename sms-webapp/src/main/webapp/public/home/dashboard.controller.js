(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashBoardCtrl', ['$rootScope','$scope', 'CrudService', 'FlashService', '$location', 'AdminService', '$cookieStore',
            function ($rootScope,$scope, CrudService, FlashService, $location, AdminService, $cookieStore) {
                $scope.loggedUser='';
                $scope.loggedUser = $rootScope.globals.currentUser.username;
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

                $scope.updateClock = function () {
                    var now = moment(),
                        second = now.seconds() * 6,
                        minute = now.minutes() * 6 + second / 60,
                        hour = ((now.hours() % 12) / 12) * 360 + 90 + minute / 12;

                    $('#hour').css("transform", "rotate(" + hour + "deg)");
                    $('#minute').css("transform", "rotate(" + minute + "deg)");
                    $('#second').css("transform", "rotate(" + second + "deg)");
                };

                $scope.timedUpdate = function () {
                    $scope.updateClock();
                    setTimeout($scope.timedUpdate, 1000);
                };

                (function (){
                    var today_date = moment().format('DD');
                    var month = moment().format('MMMM');
                    $(".dates").html(today_date);
                    $(".month_clock").html(month);

                }());


                $scope.loadStudents = function () {
                    CrudService.studentService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.students = []
                        } else {
                            $scope.students = res
                        }
                    }, function () {
                        $scope.students = []
                    })
                };

                $scope.loadScholarshipEnrollments = function () {
                    CrudService.scholarshipEnrollmentService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.scholarshipEnrollments = []
                        } else {
                            $scope.scholarshipEnrollments = res
                        }
                    }, function () {
                        $scope.scholarshipEnrollments = []
                    })
                };

                $scope.init = function () {
                    $scope.loadStudents();
                    $scope.loadScholarshipEnrollments();
                    $scope.timedUpdate();
                    AdminService.getCourses(function (data) {
                        $scope.courses = data
                    });
                };

                $scope.init();

            }]);

})();
