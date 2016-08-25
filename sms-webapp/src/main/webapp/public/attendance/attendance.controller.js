(function () {
    'use strict';

    angular
        .module('Attendance')
        .controller('AttendanceCtrl', ['$rootScope','$scope','AdminService','$http','CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
            function ($rootScope,$scope,AdminService,$http, CrudService, FlashService, ngTableParams, $state, $timeout , $uibModal) {

                $scope.init = function () {

                    $scope.attendance={};
                    $scope.attendance.attendanceDate = new Date();

                    $scope.getBranchDesc = function (branchCode){
                        return AdminService.getBranchDesc(branchCode);
                    };

                    AdminService.getConstants(function (data) {
                        $scope.status = data.attendanceStatus;
                    });

                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });

                    $scope.username='';
                    $scope.searchCriteria={};


                   /* $scope.attendance.userName=$rootScope.globals.currentUser.username;*/
                    var branchName = AdminService.getBranchDesc($rootScope.globals.currentUser.otherDetails.branch);
                    $scope.attendance.branchCode=AdminService.getBranchCode(branchName);
                    $scope.searchCriteria.branchName=branchName;
                    
                }

                $scope.statusHoliday=function () {
                    $scope.status=['HOLIDAY'];
                    $scope.attendance.attendanceDetails=[];
                    _.forEach($scope.student, function (stud) {
                        $scope.attendance.attendanceDetails.push({
                            studentName : stud.name,
                            studentCode : stud.code,
                            status:'HOLIDAY'
                        });
                    })
                }
                
                $scope.search = function () {
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

                            getData: function ($defer, params) {
                                $http.post('/student/search', $scope.searchCriteria).then(function (res) {
                                    $scope.attendance.attendanceDetails=[];
                                   _.forEach(res.data, function (stud) {
                                        $scope.attendance.attendanceDetails.push({
                                            studentName : stud.name,
                                            studentCode : stud.code,
                                            status:''
                                        });
                                    })
                                    $scope.student = res.data;

                                    $timeout(function () {
                                        params.total($scope.attendance.attendanceDetails.length);
                                        $defer.resolve($scope.attendance.attendanceDetails);
                                    }, 10);
                                }, function () {
                                    $scope.entities = []
                                    $timeout(function () {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        });
                    }
                }


                $scope.attendanceSubmit = function () {
                    $scope.attendance.attendanceDate = $scope.attendance.attendanceDate;
                    $scope.attendance.branchCode = AdminService.getBranchCode($scope.searchCriteria.branchName);
                    $scope.attendance.batchName = $scope.searchCriteria.batchName;
                    $scope.attendance.userName=$rootScope.globals.currentUser.username;
                    /*$scope.attendance.status = $scope.attendance.st.status;
*/

                    CrudService.attendanceService.Create($scope.attendance).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Attendance submit !!", true);
                        $state.reload();
                    });
                }

                $scope.init();

            }])
        .controller('AttendanceDetailCtrl', ['$scope','AdminService','$http','CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
            function ($scope,AdminService,$http, CrudService, FlashService, ngTableParams, $state, $timeout , $uibModal) {
                $scope.init = function () {

                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });
                }

                $scope.search = function () {
                    $scope.searchCriteria.durationTo= moment($scope.searchCriteria.durationFrom).add(1, 'months').startOf('month');

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

                            getData: function ($defer, params) {
                                $scope.students={};
                                $http.post('/attendance/search', $scope.searchCriteria).then(function(res) {
                                    $scope.students = res.data;
                                    $timeout(function () {
                                        params.total($scope.students.length);
                                        $defer.resolve($scope.students);
                                    }, 10);
                                }, function () {
                                    $scope.entities = []
                                    $timeout(function () {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        });
                    }
                }



                $scope.init();
                
            }]);

})();