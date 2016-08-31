(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashBoardCtrl', ['$rootScope', '$scope', 'CrudService', 'FlashService', '$location', 'AdminService', '$cookieStore', '$http',
            function ($rootScope, $scope, CrudService, FlashService, $location, AdminService, $cookieStore, $http) {
                $scope.loggedUser = '';
                $scope.loggedUser = $rootScope.globals.currentUser.username;
                $scope.loggedUserRole = $rootScope.globals.currentUser.otherDetails.role;
                $scope.loggedInBranchCode = $cookieStore.get('globals').currentUser.otherDetails.branch;

                $scope.getBranchDesc = function (branchCode) {
                    return AdminService.getBranchDesc(branchCode);
                };


                $scope.getSchemeDesc = function (schemeCode) {
                    return AdminService.getSchemeDesc(schemeCode);
                };

                $scope.updateClock = function () {
                    var now = moment(),
                        second = now.seconds() * 6,
                        minute = now.minutes() * 6 + second / 60,
                        hour = ((now.hours() % 12) / 12) * 360 + 90 + minute / 12;

                    $('#hour').css("transform", "rotate(" + hour + "deg)");
                    $('#minute').css("transform", "rotate(" + minute + "deg)");
                    $('#second').css("transform", "rotate(" + second + "deg)");
                };

                $scope.timedUpdate = function () {
                    $scope.updateClock();
                    setTimeout($scope.timedUpdate, 1000);
                };

                (function () {
                    var today_date = moment().format('DD');
                    var month = moment().format('MMMM');
                    $(".dates").html(today_date);
                    $(".month_clock").html(month);

                }());


                $scope.loadStudents = function () {
                    CrudService.studentService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.students = []
                        } else {
                            $scope.students = res
                        }
                    }, function () {
                        $scope.students = []
                    })
                };

                $scope.loadScholarshipEnrollments = function () {
                    CrudService.scholarshipEnrollmentService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.scholarshipEnrollments = []
                        } else {
                            $scope.scholarshipEnrollments = res
                        }
                    }, function () {
                        $scope.scholarshipEnrollments = []
                    })
                };

                $scope.init = function () {
                    $scope.loadStudents();
                    $scope.loadScholarshipEnrollments();
                    $scope.timedUpdate();
                    $scope.totalIncome();
                    $scope.calcEnrol();
                    $scope.totalExpense();
                    $scope.calcScholarshipEnrol();

                    AdminService.getCourses(function (data) {
                        $scope.courses = data
                    });

                    AdminService.getBatches(function (data) {
                        $scope.batches = data
                    });
                };


                $scope.calcEnrol = function () {
                    $scope.enrolled = 0;
                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    $http.post('/student/search', searchCriteria).then(function (res) {
                        $scope.enrolled = res.data.length;
                    })
                };

                $scope.calcScholarshipEnrol = function () {
                    $scope.scholarEnrolled = 0;
                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    $http.post('/scholarshipenrollment/search', searchCriteria).then(function (res) {
                        $scope.scholarEnrolled = res.data.length;
                    })
                };

                $scope.totalIncome = function () {
                    $scope.totalPayment = 0;
                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    $http.post('/payment/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        var total = 0;
                        _.forEach(datas, function (data) {
                            total = total + data.paymentInfo.amount;
                        });
                        $scope.totalPayment = total;
                    })
                };

                $scope.totalExpense = function () {
                    $scope.totalExp = 0;
                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    var total = 0;
                    $http.post('/expense/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        _.forEach(datas, function (data) {
                            total = total + data.totalAmount;
                        });
                        $scope.totalExp = total;
                    })

                };
                $scope.init();
            }])
        .controller("StudentDashboardCtrl", ["$scope", "AdminService", "$http", "$state",
            function ($scope, AdminService, $http, $state) {
                $scope.init = function () {
                    $scope.enrolledBranches = [];
                    $scope.enrolledBranches.branchCode = '';
                    var flag = false;

                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    $http.post('/student/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        _.forEach(datas, function (data) {
                            for (var i = 0; i < $scope.enrolledBranches.length; i++) {
                                if ($scope.enrolledBranches[i].branchCode == data.branchCode) {
                                    $scope.enrolledBranches[i].amount = $scope.enrolledBranches[i].amount + 1;
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == false) {
                                $scope.enrolledBranches.push({
                                    amount: 1,
                                    branchCode: data.branchCode
                                })
                            }
                            flag = false;
                        })
                    })

                }
                $scope.init();
                $scope.getBranchDesc = AdminService.getBranchDesc;
            }])
        .controller("ScholarDashboardCtrl", ["$scope", "AdminService", "$http", "$state","$uibModal",
            function ($scope, AdminService, $http, $state,$uibModal) {
                $scope.init = function () {
                    $scope.enrolledBranches = [];
                    $scope.enrolledBranches.branchCode = '';
                    var flag = false;

                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    $http.post('/scholarshipenrollment/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        $scope.scholars = res.data;
                        _.forEach(datas, function (data) {
                            for (var i = 0; i < $scope.enrolledBranches.length; i++) {
                                if ($scope.enrolledBranches[i].branchCode == data.branchCode) {
                                    $scope.enrolledBranches[i].amount = $scope.enrolledBranches[i].amount + 1;
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == false) {
                                $scope.enrolledBranches.push({
                                    amount: 1,
                                    branchCode: data.branchCode,
                                })
                            }
                            flag = false;
                        })
                    })

                }


                    $scope.changeBranch = function (branch) {
                        var modalInstance = $uibModal.open({
                            animation: true,
                            templateUrl: 'scholarMarketingEmployee.html',
                            controller: 'ScholarMarketingEmployeeCtrl',
                            backdrop: 'static',
                            resolve: {
                                datas: function () {
                                    return $scope.scholars;
                                },
                                branch:function () {
                                    return branch;
                                }
                            }
                        });
                }
                $scope.init();
                $scope.getBranchDesc = AdminService.getBranchDesc;


            }])
        .controller("ScholarMarketingEmployeeCtrl", function ($scope, $uibModalInstance, $state,datas,branch,AdminService) {
            $scope.marketingEmployees = [];
            $scope.branchName=AdminService.getBranchDesc(branch);
            var flag = false;
            _.forEach(datas, function (data) {
                if(data.branchCode==branch) {
                    for (var i = 0; i < $scope.marketingEmployees.length; i++) {
                        if ($scope.marketingEmployees[i].marketingEmployeeCode == data.marketingEmployeeCode) {
                            $scope.marketingEmployees[i].amount = $scope.marketingEmployees[i].amount + 1;
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        $scope.marketingEmployees.push({
                            amount: 1,
                            marketingEmployeeCode: data.marketingEmployeeCode
                        })
                    }
                    flag = false;
                }
            })
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.getMarketingEmployeeName = AdminService.getMarketingEmployeeName
        })
        .controller("IncomeDashboardCtrl", ["$scope", "AdminService", "$http", "$state",
            function ($scope, AdminService, $http, $state) {
                $scope.changeLocation = function (branchName) {
                    $state.go('home.payment-list', {branch: branchName});
                }

                $scope.init = function () {
                    $scope.incomeBranches = [];
                    $scope.incomeBranches.branchName = '';
                    var flag = false;

                    var searchCriteria = {};
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    $http.post('/payment/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        _.forEach(datas, function (data) {
                            for (var i = 0; i < $scope.incomeBranches.length; i++) {
                                if ($scope.incomeBranches[i].branchName == data.branchName) {
                                    $scope.incomeBranches[i].amount = $scope.incomeBranches[i].amount + data.paymentInfo.amount;
                                    flag = true;
                                    break;
                                }

                            }
                            if (flag == false) {
                                $scope.incomeBranches.push({
                                    amount: data.paymentInfo.amount,
                                    branchName: data.branchName

                                })
                            }
                            flag = false;
                        })
                    })

                }
                $scope.init();


            }])
        .controller("ExpenseDashboardCtrl", ["$scope", "AdminService", "$http", "$state",
            function ($scope, AdminService, $http, $state) {
                $scope.expenses = [];
                $scope.changeLocation = function () {
                    $state.go('home.expense-list');
                }

                $scope.init = function () {
                    var searchCriteria = {};
                    var flag = false;
                    searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                    searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                    $http.post('/expense/search', searchCriteria).then(function (res) {
                        var datas = res.data;
                        _.forEach(datas, function (data) {
                            for (var i = 0; i < $scope.expenses.length; i++) {
                                if ($scope.expenses[i].branchName == AdminService.getBranchDesc(data.branchCode)) {
                                    $scope.expenses[i].amount = $scope.expenses[i].amount + data.totalAmount;
                                    flag = true;
                                    break;
                                }
                            }
                                if (flag == false) {
                                    $scope.expenses.push({
                                        amount: data.totalAmount,
                                        branchName: AdminService.getBranchDesc(data.branchCode)
                                    })
                                }
                                flag = false;
                        })
                    })
                }
                $scope.init();


            }]);

})();