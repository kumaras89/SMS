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
        .controller('ScholarshipEnrollmentListCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout','$http',
            function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, $http) {
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
                        $http.get('/scholarshipEnrollment').then(function(res) {
                            var data = res.data
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
        .controller('ScholarshipEnrollmentDetailCtrl', ['$scope', '$stateParams', 'CrudService', '$http',
            function ($scope, $stateParams, CrudService, $http) {

                $scope.loadScholarshipEnrollment = function () {
                    $http.get('/scholarshipEnrollment/'+$stateParams.id).then(function (res) {
                        $scope.scholarshipEnrollment = res.data
                    })
                }
                $scope.loadScholarshipEnrollment();

            }])
        .controller('ScholarshipEnrollmentCreationCtrl', ['$scope', '$http', 'FlashService', '$state', 'AdminService',
            function ($scope, $http, FlashService, $state, AdminService) {

                $scope.scholorshipEnrollment = [];
                $scope.scholorshipEnrollment.educationDetails = [{},{},{},{}];


                $scope.createNewScholarshipEnrollment = function () {

                    $http.post('/scholarshipEnrollment', $scope.scholarshipSumarized).then(function(){
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.scholarshipenrollment-list');
                    })
                }

                $scope.calculateAge = function (birthday) { // birthday is a date
                    var today = new Date();
                    var birthDate = new Date(birthday);
                    var months = (today.getFullYear() - birthDate.getFullYear()) * 12;
                    months -= birthDate.getMonth() + 1;
                    months += today.getMonth();
                    $scope.scholarshipEnrollment.age = Math.ceil(months <= 0 ? '' : months / 12) || '';
                    return $scope.scholarshipEnrollment.age;
                }

                $scope.updateScholarshipEnrollment = function(){
                    $scope.scholarshipSumarized = $scope.scholorshipEnrollment;
                    $scope.scholarshipSumarized.status = 'CREATED';
                    // $scope.scholarshipEnrollment.branchCode = AdminService.getBranchCode($scope.student.branchName);
                    $scope.scholarshipSumarized.educationDetails = _.filter($scope.scholarshipEnrollment.educationDetails, function(ed){
                        return  ed.examPassed != undefined && ed.examPassed != '';
                    });
                };


                $scope.init = function ()
                {
                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });

                    AdminService.getYearOfPassing(function (data) {
                        $scope.yearOfPass = data;
                    });
                }
                $scope.init();

            }]);

})();     
    