﻿(function () {
    'use strict';

    angular
        .module('Student')
        .controller('StudentListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout',
            function ($scope, CrudService, FlashService, ngTableParams, $state, AdminService, $timeout) {
                $scope.viewStudent = function (userId) {
                    $state.go('home.student-detail' , {id: userId});
                };

                $scope.getBranchDesc = function (branchCode){
                    return AdminService.getBranchDesc(branchCode);
                };

                $scope.getCourseDesc = function (courseCode){
                    return AdminService.getCourseDesc(courseCode);
                };

                $scope.getSchemeDesc = function (schemeCode){
                    return AdminService.getSchemeDesc(schemeCode);
                };

                $scope.createNewStudent = function () {
                    $state.go('home.student-creation');
                };

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function($defer, params) {
                        CrudService.studentService.GetAll().then(function(data) {
                            if(data.message) {
                                $scope.students = [];
                                FlashService.Error(data.message)
                            } else {
                                $timeout(function() {
                                    params.total(data.length);
                                    $defer.resolve(data);
                                }, 10);
                            }
                        }, function() {
                            $scope.students = []
                        })
                    }
                });
            }])
        .controller('StudentDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'AdminService', '$location',
            function ($scope, $stateParams, CrudService, AdminService, $location) {

                $scope.loadStudent = function () {
                    CrudService.studentService.GetById($stateParams.id).then(function (res) {
                        $scope.student = res
                    })
                }

                $scope.getBranchDesc = function (branchCode){
                    return AdminService.getBranchDesc(branchCode);
                };

                $scope.getCourseDesc = function (courseCode){
                    return AdminService.getCourseDesc(courseCode);
                };

                $scope.getSchemeDesc = function (schemeCode){
                    return AdminService.getSchemeDesc(schemeCode);
                };

                $scope.getMarketingEmployeeName = function (schemeCode){
                    return AdminService.getMarketingEmployeeName(schemeCode);
                };

                $scope.goToFms = function(code) {
                    $location.path('/fms/STUDENT/'+code);
                }


                $scope.loadStudent();
            }])
        .controller('StudentCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, CrudService, FlashService, $state, AdminService) {
                $scope.student = {};
                $scope.student.educationDetails = [{},{},{},{}];
                $scope.student.guardians = [{},{},{},{}];
                $scope.student.otherLanguages = [{},{},{}];

                $scope.createNewStudent = function () {
                    CrudService.studentService.Create($scope.studentSumarized).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.student-list');
                    });
                }

                $scope.calculateAge = function (birthday) { // birthday is a date
                    var today = new Date();
                    var birthDate = new Date(birthday);
                    var months = (today.getFullYear() - birthDate.getFullYear()) * 12;
                    months -= birthDate.getMonth() + 1;
                    months += today.getMonth();
                    $scope.student.age = Math.ceil(months <= 0 ? '' : months / 12) || '';
                    return $scope.student.age;
                }

                $scope.getFeesParticularDesc = function (feesParticularCode){
                    return AdminService.getFeesParticularDesc(feesParticularCode);
                };

                $scope.updateStudent = function(){
                    $scope.student.status = 'CREATED';
                    $scope.student.schemeCode = AdminService.getSchemeCode($scope.student.schemeName);
                    $scope.student.feesInfos = AdminService.getSchemeFeesInfo($scope.student.schemeCode);
                    $scope.student.branchCode = AdminService.getBranchCode($scope.student.branchName);
                    $scope.student.courseCode = AdminService.getCourseCode($scope.student.courseName);
                    $scope.student.marketingEmployeeCode = AdminService.getMarketingEmployeeCode($scope.student.referalName);
                    $scope.studentSumarized = $scope.student;
                    $scope.studentSumarized.educationDetails = _.filter($scope.student.educationDetails, function(ed){
                        return  ed.examPassed != undefined && ed.examPassed != '';
                    });
                    $scope.studentSumarized.guardians = _.filter($scope.student.guardians, function(ed){
                        return  ed.relationShip != undefined && ed.relationShip != '';
                    });
                    $scope.studentSumarized.otherLanguages = _.filter($scope.student.otherLanguages, function(ed){
                       return  ed.name != undefined && ed.name != '';
                   });

                };



                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });

                    AdminService.getMarketingEmployees(function (data) {
                        $scope.marketingEmployees = data;
                    });

                    AdminService.getBranches(function (data) {
                        $scope.branches = data;
                        $scope.branchNames = _.pluck(data, "name")
                    });

                    AdminService.getSchemes(function (data) {
                        $scope.schemes = data;
                        $scope.schemeNames = _.pluck(data, "name")
                    });

                    AdminService.getCourses(function (data) {
                        $scope.courseNames = _.pluck(data, "name")
                    });

                    AdminService.getYearOfPassing(function (data) {
                        $scope.yearOfPass = data;
                    });
                }

                $scope.init();

            }]);

})();
