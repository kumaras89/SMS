(function () {
    'use strict';

    angular
        .module('ScholarshipEnrollment')
        .filter('ageFilter', function () {
            function calculateAge(birthday) { // birthday is a date
                var ageDifMs = Date.now() - new Date(birthday).getTime();
                var ageDate = new Date(ageDifMs); // miliseconds from epoch
                return Math.abs(ageDate.getUTCFullYear() - 1970);
            }

            return function (birthdate) {
                return calculateAge(birthdate);
            };
        })
        .controller('ScholarshipEnrollmentListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout',
            function ($scope, CrudService, FlashService, ngTableParams, $state, AdminService, $timeout) {
                $scope.viewScholarshipEnrollment = function (userId) {
                    $state.go('home.scholarshipenrollment-detail' , {id: userId});
                };




                $scope.createNewScholarshipEnrollment = function () {
                    $state.go('home.scholarshipenrollment-creation');
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
                        CrudService.scholarshipEnrollmentService.GetAll().then(function(data) {
                            if(data.message) {
                                $scope.scholarshipEnrollment = [];
                                FlashService.Error(data.message)
                            } else {
                                $timeout(function() {
                                    params.total(data.length);
                                    $defer.resolve(data);
                                }, 10);
                            }
                        }, function() {
                            $scope.scholarshipEnrollment = []
                        })
                    }
                });
            }])
        .controller('ScholarshipEnrollmentDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'AdminService', '$location',
            function ($scope, $stateParams, CrudService, AdminService, $location) {

                $scope.loadStudent = function () {
                    CrudService.scholarshipEnrollmentService.GetById($stateParams.id).then(function (res) {
                        $scope.scholarshipEnrollment = res
                    })
                }
                $scope.loadScholarshipEnrollment();
            }])
        .controller('ScholarshipEnrollmentCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'AdminService',
            function ($scope, CrudService, FlashService, $state, AdminService) {

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

                $scope.createNewScholarshipEnrollment = function () {

                    CrudService.scholarshipEnrollmentService.Create($scope.studentScholar).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.scholarshipenrollment-list');
                    });
                }

                $scope.removeGuardian = function () {
                    $scope.guardians.splice(-1, 1);
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
