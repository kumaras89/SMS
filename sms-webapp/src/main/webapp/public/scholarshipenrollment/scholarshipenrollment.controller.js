(function () {
    'use strict';

    angular
        .module('ScholarshipEnrollment')
        .controller('ScholarshipEnrollmentListCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout','$http',
            function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, $http) {

                $scope.init = function() {
                    AdminService.getBranches(function (data) {
                        $scope.branches = data;
                        $scope.branchNames = _.pluck(data, "name");
                    });

                    AdminService.getConstants(function (data) {
                        $scope.statusList = data.scholarStatuses;
                    });
                    $scope.searchCriteria = {}
                }

                $scope.editScholarshipEnrollment = function (userId) {
                    $state.go('home.scholarshipenrollment-edit',{id: userId});
                };

                $scope.viewScholarshipEnrollment = function (userId) {
                    $state.go('home.scholarshipenrollment-detail' , {id: userId});
                }

                $scope.createNewScholarshipEnrollment = function () {
                    $state.go('home.scholarshipenrollment-creation');
                }

                $scope.search = function () {
                    if( $scope.searchCriteria.status===""){
                        $scope.searchCriteria={};
                    }
                    if($scope.searchCriteria.durationTo){
                        $scope.searchCriteria.durationTo= moment($scope.searchCriteria.durationTo).add(1,'days');
                    }
                    if ($scope.tableParams) {
                        $scope.tableParams.reload()
                    } else {
                        $scope.tableParams = new ngTableParams({
                            page: 1,            // show first pagez
                            count: 10,          // count per page
                            sorting: {
                                name: 'asc'     // initial sorting
                            }
                        }, {
                            total: 0,           // length of data
                            getData: function($defer, params) {
                                $http.post('/scholarshipenrollment/search', $scope.searchCriteria).then(function(res) {
                                    var data = res.data
                                    $timeout(function() {
                                        params.total(data.length);
                                        $defer.resolve(data);
                                    }, 10);
                                }, function() {
                                    $scope.entities = []
                                    $timeout(function() {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        });
                    }
                }

                $scope.init()
            }])
        .controller('ScholarshipEnrollmentDetailCtrl', ['$scope', '$stateParams', 'CrudService', '$http',
            function ($scope, $stateParams, CrudService, $http) {

                $scope.loadScholarshipEnrollment = function () {
                    $http.get('/scholarshipenrollment/'+$stateParams.id).then(function (res) {
                        $scope.scholarshipSummarized = res.data
                    })
                }
                $scope.loadScholarshipEnrollment();

            }])
        .controller('ScholarshipEnrollmentEditCtrl',['$scope','$http','$stateParams','FlashService','$state','AdminService','StorageService',
            function ($scope, $http,$stateParams, FlashService, $state, AdminService, StorageService) {
                $scope.loadScholarshipEnrollment = function () {
                    $http.get('/scholarshipenrollment/'+$stateParams.id).then(function (res) {
                        $scope.scholarshipEnrollment = res.data
                        $scope.scholarshipEnrollment.branch = AdminService.getBranchDesc(res.data.branchCode);
                        $scope.scholarshipEnrollment.marketingEmployee = AdminService.getMarketingEmployeeName(res.data.marketingEmployeeCode);
                        $scope.scholarshipEnrollment.dateOfBirth = new Date(res.data.dateOfBirth);
                        
                    })
                }
                $scope.loadScholarshipEnrollment();
                $scope.initialize = function(){
                    $scope.scholarshipEnrollment = {};
                    $scope.scholarshipEnrollment.educationDetails = [{},{},{},{}];
                    $scope.scholarshipEnrollment.gender='MALE';
                    $scope.scholarshipEnrollment.maritalStatus='MARRIED';
                    $scope.scholarshipEnrollment.annualIncome='Upto 50000';
                };
                $scope.initialize();


                $scope.createNewScholarshipEnrollment = function () {

                    $http.post('/scholarshipenrollment', $scope.scholarshipSummarized).then(function(){
                        window.scrollTo(0,0);
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
                    $scope.scholarshipSummarized.status = 'INSERTED';
                    $scope.scholarshipSummarized.branchCode = AdminService.getBranchCode($scope.scholarshipEnrollment.branch);
                    $scope.scholarshipSummarized.marketingEmployeeCode = AdminService.getMarketingEmployeeCode($scope.scholarshipEnrollment.marketingEmployee);
                    $scope.scholarshipSummarized.educationDetails = _.filter($scope.scholarshipEnrollment.educationDetails, function(ed){
                        return  ed.examPassed != undefined && ed.examPassed != '';
                    });
                };

                $scope.updateScholarship = function () {
                    var res =  $http.put('/scholarshipenrollment'+'/' + $scope.scholarshipSummarized.applicationNumber , $scope.scholarshipSummarized).then(function(res){
                        StorageService.clearStorage('/scholarshipenrollment')
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.scholarshipenrollment-list');
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
                    AdminService.getMarketingEmployees(function (data) {
                        $scope.marketingEmployees = data;
                    });
                }
                $scope.init();

            }
        ])
        .controller('ScholarshipEnrollmentCreationCtrl', ['$scope', '$http', 'FlashService', '$state', 'AdminService',
            function ($scope, $http, FlashService, $state, AdminService) {

                $scope.initialize = function(){
                    $scope.scholarshipEnrollment = {};
                    $scope.scholarshipEnrollment.educationDetails = [{},{},{},{}];
                    $scope.scholarshipEnrollment.gender='MALE';
                    $scope.scholarshipEnrollment.maritalStatus='MARRIED';
                    $scope.scholarshipEnrollment.annualIncome='Upto 50000';
                };
                $scope.initialize();


                $scope.createNewScholarshipEnrollment = function () {

                    $http.post('/scholarshipenrollment', $scope.scholarshipSummarized).then(function(){
                        window.scrollTo(0,0);
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
                    $scope.scholarshipSummarized.status = 'INSERTED';
                    $scope.scholarshipSummarized.branchCode = AdminService.getBranchCode($scope.scholarshipEnrollment.branch);
                    $scope.scholarshipSummarized.marketingEmployeeCode = AdminService.getMarketingEmployeeCode($scope.scholarshipEnrollment.marketingEmployee);
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
                    AdminService.getMarketingEmployees(function (data) {
                        $scope.marketingEmployees = data;
                    });
                    $scope.getBranchDesc = function (branchCode){
                        return AdminService.getBranchDesc(branchCode);
                    };
                    AdminService.getBranches(function (data) {
                        $scope.branchNames = _.pluck(data, "name");
                    });
                }
                $scope.init();

            }]);

})();     
    