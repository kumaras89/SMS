(function () {
    'use strict';

    angular
        .module('Student')
        .controller('StudentListCtrl', ['$scope', 'CrudService', 'FlashService', '$location', 'AdminService',
            function ($scope, CrudService, FlashService, $location, AdminService) {
                $scope.viewStudent = function (userId) {
                    $location.path('/student-detail/' + userId);
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
        .controller('StudentDetailCtrl', ['$scope', '$routeParams', 'CrudService', 'AdminService', '$location',
            function ($scope, $routeParams, CrudService, AdminService, $location) {

                $scope.loadStudent = function () {
                    CrudService.studentService.GetById($routeParams.id).then(function (res) {
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
        .controller('StudentCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$location', 'AdminService',
            function ($scope, CrudService, FlashService, $location, AdminService) {

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
                    $scope.student.status = 'CREATED';

                    $scope.student.branchCode = AdminService.getBranchCode($scope.branchName);
                    $scope.student.schemeCode = AdminService.getSchemeCode($scope.schemeName);
                    $scope.student.courseCode = AdminService.getCourseCode($scope.courseName);
                    $scope.student.marketingEmployeeCode = AdminService.getMarketingEmployeeCode($scope.referalName);


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
                }

                $scope.init();

            }]);

})();
