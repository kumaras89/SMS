(function () {
    'use strict';

    angular
        .module('ScholarshipEnrollment')
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
                        $scope.scholarshipSummarized = res.data
                    })
                }
                $scope.loadScholarshipEnrollment();

            }])
        .controller('ScholarshipEnrollmentCreationCtrl', ['$scope', '$http', 'FlashService', '$state', 'AdminService',
            function ($scope, $http, FlashService, $state, AdminService) {

                $scope.scholarshipEnrollment = {};
                $scope.scholarshipEnrollment.educationDetails = [{},{},{},{}];


                $scope.createNewScholarshipEnrollment = function () {

                    $http.post('/scholarshipEnrollment', $scope.scholarshipSummarized).then(function(){
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
                    $scope.scholarshipEnrollment.age =  $scope.calculateAge($scope.scholarshipEnrollment.dateOfBirth)
                    $scope.scholarshipSummarized = {}
                    angular.copy($scope.scholarshipEnrollment, $scope.scholarshipSummarized);
                    $scope.scholarshipSummarized.status = 'CREATED';
                    // $scope.scholarshipEnrollment.branchCode = AdminService.getBranchCode($scope.student.branchName);
                    $scope.scholarshipSummarized.educationDetails = _.filter($scope.scholarshipEnrollment.educationDetails, function(ed){
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
    