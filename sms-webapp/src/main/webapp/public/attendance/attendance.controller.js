(function () {
    'use strict';

    angular
        .module('Attendance')
        .filter('mySum', function () {
            return function (items) {
                var sum = 0;
                angular.forEach(items.dateList, function (item, index) {
                    if (item.status=='PRESENT') {
                        sum += 1;
                    }
                })
                return sum;
            }
        })
        .controller('AttendanceCtrl', ['$rootScope','$scope','AdminService','$http','CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$uibModal',
            function ($rootScope,$scope,AdminService,$http, CrudService, FlashService, ngTableParams, $state, $timeout , $uibModal) {
                
                $scope.batchChanged = function (batch) {
                    $scope.durationFrom=new Date(batch.durationFrom);
                    $scope.durationTo=new Date(batch.durationTo);
                    $scope.batchName= batch.name;
                }
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
                        $scope.batches=data;
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
                                $scope.attendance.attendanceDetails=[];
                                $scope.searchCriteria.bachName=$scope.batchName;
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
                    $scope.attendance.batchName = $scope.batchName;
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

                $scope.takeAttendance= function () {
                    $state.go('home.attendance');
                }
                $scope.updateAttendance= function () {
                    $state.go('home.attendance-update');
                }
                $scope.slic=function (word) {
                    return word.charAt(0);
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
                                     var workingDays= res.data[0].dateList.length;
                                    _.forEach(res.data[0].dateList, function (day) {
                                        if(day.status=='HOLIDAY')
                                        workingDays -=1
                                    })
                                    $scope.workingDays=workingDays;
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

                $scope.printToCart = function(printSectionId) {
                    var innerContents = document.getElementById(printSectionId).innerHTML;
                    var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
                    popupWinindow.document.open();
                    popupWinindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + innerContents + '</html>');
                    popupWinindow.document.close();
                };

                $scope.init();
                
            }])
        .controller('AttendanceUpdateCtrl', ['$scope','AdminService','$http','CrudService', 'FlashService', 'ngTableParams', '$state', '$timeout', '$rootScope',
            function ($scope,AdminService,$http, CrudService, FlashService, ngTableParams, $state, $timeout , $rootScope) {
                $scope.init = function () {
                    $scope.searchCriteria={};
                    $scope.searchCriteria.userName=$rootScope.globals.currentUser.username;
                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });
                    AdminService.getConstants(function (data) {
                        $scope.status = data.attendanceStatus;
                    });
                }

                $scope.search = function () {
                    $scope.searchCriteria.durationFrom = new Date($scope.searchCriteria.durationFrom).setHours(0, 0, 0, 0);
                    $scope.searchCriteria.durationTo = new Date($scope.searchCriteria.durationFrom).setHours(23, 59, 59, 59);

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
                                $scope.students=[];
                                $http.post('/attendance/search', $scope.searchCriteria).then(function(res) {
                                    var datas = res.data;
                                    _.forEach(datas, function (data) {
                                        $scope.students.push({
                                            name : data.name,
                                            id : data.dateList[0].id,
                                            status: data.dateList[0].status,
                                        });
                                    })


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
                
                $scope.attendanceUpdate= function (id,status) {
                    $scope.attendance={};
                    $scope.attendance.id=id;
                    $scope.attendance.status=status;
                    var res =  $http.put('/attendance/' + id, status).then(function(res){
                            window.scrollTo(0,0);
                            FlashService.Success('Successfully Modified!!');
                            $scope.loadRol();

                    });
                }
                    




                $scope.init();

            }]);

})();