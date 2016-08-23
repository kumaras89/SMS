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
                        $scope.commonAttributes = data;
                    });

                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });

                    $scope.username='';
                    $scope.searchCriteria={};


                    $scope.attendance.userName=$rootScope.globals.currentUser.username;
                    var branchName = AdminService.getBranchDesc($rootScope.globals.currentUser.otherDetails.branch);
                    $scope.students={};
                    /*$scope.attendance.userName=$scope.username;*/
                    $scope.attendance.branchCode=AdminService.getBranchCode(branchName);
                    $scope.searchCriteria.branchName=branchName;

                    $scope.attendance.attendanceDetails=[];
                    
                }

                /*$scope.statusChanged=function (index,st) {
                    $scope.attendance.attendanceDetails[$index].status=st;
                }
*/
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
                                    var data = res.data;
                                           _.forEach(data, function (stud) {
                                        $scope.attendance.attendanceDetails.push({
                                            studentName : stud.name,
                                            studentCode : stud.code,
                                            
                                        });

                                    })
                                    $timeout(function () {
                                        params.total(data.length);
                                        $defer.resolve(data);
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
                    $scope.attendance.attendanceDate=$scope.attendance.attendanceDate;
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

                /*$scope.getStudents= function () {
                    $scope.students=[];
                    $http.post('/student/search', searchCriteria).then(function(res) {
                        var data=res.data;
                        _.forEach(data, function (stud) {
                            $scope.students.push({
                                studentName : stud.name,
                                studentCode : stud.code,
                            });

                        })


                    })
                }*/

                $scope.search = function () {

                        if($scope.searchCriteria.durationTo){
                            $scope.searchCriteria.durationTo= moment($scope.searchCriteria.durationTo).add(1,'days');
                        }
                                    $http.post('/attendance/search', $scope.searchCriteria).then(function(res) {
                                        $scope.attendance = res.data;
                                        $scope.students=[];

                                            _.forEach($scope.attendance[0].attendanceDetails, function (atd) {

                                                $scope.students.push({
                                                    name: atd.studentName,
                                                    code: atd.code,
                                                })
                                        })

                                    })
                    }



                $scope.init();
                
            }]);

})();