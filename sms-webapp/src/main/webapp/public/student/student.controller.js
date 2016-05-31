(function () {
    'use strict';

    angular
        .module('Student')
        .controller('StudentListCtrl', ['$scope', 'CrudService', 'FlashService', '$location', 'AdminService',
            function ($scope, CrudService, FlashService, $location, AdminService) {
                $scope.editStudent = function (userId) {
                    $location.path('/student-detail/' + userId);
                };

                $scope.deleteStudent = function (id) {
                    CrudService.studentService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                        $scope.loadStudents();
                    });

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
                    $location.path('/student-creation');
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


            }])
        .controller('StudentDetailCtrl', ['$scope', '$routeParams', 'CrudService', 'FlashService', '$location',
            function ($scope, $routeParams, CrudService, FlashService, $location) {

                $scope.updateStudent = function () {
                    CrudService.studentService.Update($scope.student).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $location.path('/student-list');
                    });

                };

                $scope.cancel = function () {
                    $location.path('/student-list');
                };

                $scope.loadStudent = function () {
                    CrudService.studentService.GetById($routeParams.id).then(function (res) {
                        $scope.student = res
                    })
                }

                $scope.loadStudent();
            }])
        .controller('StudentCreationCtrl', ['$scope', 'CrudService', 'StudentService', 'FlashService', '$location', 'AdminService',
            function ($scope, CrudService, StudentService, FlashService, $location, AdminService) {

                $scope.educationDetails = [];
                $scope.guardians = [];

                // to load default once
                $scope.educationDetails.push({
                    examPassed: '',
                    instituteName: '',
                    groupName: '',
                    passingYear: '',
                    percentageObtained: '',
                    remark: ''
                });

                $scope.guardians.push({
                    isEmployed: '',
                    name: '',
                    occupation: '',
                    monthlyIncome: '',
                    annualIncome: '',
                    gender: '',
                    relationShip: '',
                    phoneNumber: ''
                });

                $scope.createNewStudent = function () {

                    $scope.guardians.forEach(function (guaridian){
                        guaridian.annualIncome = guaridian.monthlyIncome * 12;
                    });

                    $scope.student.address = $scope.address;
                    $scope.student.guardians = $scope.guardians;
                    $scope.student.educationDetails = $scope.educationDetails;

                    $scope.student.branchCode = AdminService.getBranchCode($scope.branchName);
                    $scope.student.schemeCode = AdminService.getSchemeCode($scope.schemeName);
                    $scope.student.courseCode = AdminService.getCourseCode($scope.courseName);

                    CrudService.studentService.Create($scope.student).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $location.path('/student-list');
                    });
                }

                $scope.removeGuardian = function () {
                    $scope.guardians.splice(-1, 1);
                };

                $scope.addGuardian = function () {
                    $scope.guardians.push({
                        isEmployed: '',
                        name: '',
                        occupation: '',
                        monthlyIncome: '',
                        annualIncome: '',
                        gender: '',
                        relationShip: '',
                        phoneNumber: ''
                    });
                };

                $scope.removeEducationDetail = function () {
                    $scope.educationDetails.splice(-1, 1);
                };

                $scope.addEducationDetail = function () {
                    $scope.educationDetails.push({
                        examPassed: '',
                        instituteName: '',
                        groupName: '',
                        passingYear: '',
                        percentageObtained: '',
                        remark: ''
                    });
                };

                $scope.getFeesParticularDesc = function (feesParticularCode){
                    return AdminService.getFeesParticularDesc(feesParticularCode);
                };

                $scope.init = function () {
                    StudentService.getReligions(function (data) {
                        $scope.religions = data;
                    });

                    StudentService.getCaste(function (data) {
                        $scope.castes = data;
                    });

                    StudentService.getMaritalStatus(function (data) {
                        $scope.maritalStatus = data;
                    });

                    StudentService.getGender(function (data) {
                        $scope.genders = data;
                    });

                    StudentService.getRatings(function (data) {
                        $scope.ratings = data;
                    });

                    StudentService.getRelations(function (data) {
                        $scope.relations = data;
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
                }

                $scope.init();

            }]);

})();
