(function () {
    'use strict';

    angular
        .module('Student').directive('innerHtmlBind', function() {
            return {
                restrict: 'A',
                scope: {
                    inner_html: '=innerHtml'
                },
                link: function (scope, element, attrs) {
                    scope.inner_html = element.html();
                }
            }
        })
        .controller('StudentListCtrl', ['$scope', 'CrudService', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout','$uibModal',
            function ($scope, CrudService, FlashService, ngTableParams, $state, AdminService, $timeout, $uibModal) {

                $scope.applicationNumber = '';

                $scope.searchStudentScholarship = function() {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        templateUrl: 'ScholarshipPopup.html',
                        controller: 'StudentModalCtrl'
                    });

                    modalInstance.result.then(function (applicationNumber) {
                        $scope.applicationNumber = applicationNumber;
                    }, function () {
                        //$log.info('Modal dismissed at: ' + new Date());
                    });
                };


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
                        $scope.src = '/document/download/' + $scope.student.fmsPhotoId + '/photo.jpg';
                    })
                }

                $scope.getFeesParticularDesc = function (feesParticularCode){
                    return AdminService.getFeesParticularDesc(feesParticularCode);
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

                $scope.getMarketingEmployeeName = function (schemeCode){
                    return AdminService.getMarketingEmployeeName(schemeCode);
                };

                $scope.goToFms = function(code) {
                    $location.path('/home/fms/STUDENT/'+code);
                }

                $scope.goToPayment = function(code) {
                    $location.path('/home/payment-detail/'+code);
                }


                $scope.loadStudent();
            }])
        .controller('StudentCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'AdminService','$stateParams','$http',
            function ($scope, CrudService, FlashService, $state, AdminService, $stateParams, $http) {

                $scope.student = {};

                $scope.loadStudentScholarShip = function () {
                    if ($stateParams.applicationNumber != undefined && $stateParams.applicationNumber != '') {
                        $http.get('/student/studentScholarship/' + $stateParams.applicationNumber).then(function (res) {
                            if (res.data) {
                                $scope.student = res.data;
                                $scope.initialize();
                            }
                        })
                    } else {
                        $scope.initialize();
                        $scope.student.educationDetails = [{}, {}, {}, {}];
                    }
                };

                $scope.initialize = function(){
                    $scope.student.guardians = [{}, {}, {}, {}];
                    $scope.student.otherLanguages = [{}, {}, {}];
                    $scope.student.sslcMarkDetails = {};
                    $scope.student.sslcMarkDetails.additionalDetails = {};
                    $scope.student.hscMarkDetails = {};
                    $scope.student.hscMarkDetails.additionalDetails = {};
                    $scope.student.sslcMarkDetails.subjects = [{'name': 'Tamil', 'totalMark': 100},
                        {'name': 'English', 'totalMark': 100},
                        {'name': 'Maths', 'totalMark': 100},
                        {'name': 'Science', 'totalMark': 100},
                        {'name': 'Social Science', 'totalMark': 100},
                        {}
                    ];
                    $scope.student.hscMarkDetails.subjects = [{'name': 'Tamil', 'totalMark': 200},
                        {'name': 'English', 'totalMark': 200}
                        , {}, {}, {}, {}, {}, {}];

                };

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
                };

                $scope.totalValue = function (subjects) {
                    var sum = 0;
                    angular.forEach(subjects, function (subject, index) {
                        if (subject.securedMark) {
                            sum += subject.securedMark;
                        }
                    });
                    return sum;
                };

                $scope.sslcTotalMark = function (subjects) {
                    $scope.student.sslcMarkDetails.totalMarks = $scope.totalValue(subjects);
                    return $scope.student.sslcMarkDetails.totalMarks;
                };

                $scope.hscTotalMark = function (subjects) {
                    $scope.student.hscMarkDetails.totalMarks = $scope.totalValue(subjects);
                    return $scope.student.hscMarkDetails.totalMarks;
                };

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
                    $scope.studentSumarized = {};

                    //deep copy of student
                    angular.copy($scope.student,$scope.studentSumarized);

                    $scope.studentSumarized.educationDetails = _.filter($scope.student.educationDetails, function(ed){
                        return  ed.examPassed != undefined && ed.examPassed != '';
                    });
                    $scope.studentSumarized.guardians = _.filter($scope.student.guardians, function(ed){
                        return  ed.relationShip != undefined && ed.relationShip != '';
                    });
                    $scope.studentSumarized.otherLanguages = _.filter($scope.student.otherLanguages, function(ed){
                       return  ed.name != undefined && ed.name != '';
                   });

                    $scope.studentSumarized.sslcMarkDetails.subjects = _.filter($scope.student.sslcMarkDetails.subjects, function(ed){
                        return  ed.name != undefined && ed.name != '';
                    });

                    $scope.studentSumarized.hscMarkDetails.subjects = _.filter($scope.student.hscMarkDetails.subjects, function(ed){
                        return  ed.name != undefined && ed.name != '';
                    });

                    if(! $scope.sslcEnable){
                        delete $scope.studentSumarized.sslcMarkDetails;
                    }
                    if(! $scope.hscEnable){
                        delete $scope.studentSumarized.hscMarkDetails;
                    }

                };



                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });

                   /* AdminService.getBranches(function (data) {
                       $scope.branches = data;
                       $scope.branchNames = _.pluck(data, "name")
                     });*/

                    AdminService.getMarketingEmployees(function (data) {
                        $scope.marketingEmployees = data;
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
                $scope.loadStudentScholarShip();

            }])
        .controller("StudentModalCtrl", function ($scope, $uibModalInstance, $state) {
            $scope.ok = function () {
                if($scope.applicationNumber != undefined && $scope.applicationNumber != ''){
                    $state.go('home.student-creation',{applicationNumber: $scope.applicationNumber});
                    $uibModalInstance.close();
                }
            };

            $scope.continue = function () {
                $state.go('home.student-creation');
                $uibModalInstance.close();
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
    });


})();
