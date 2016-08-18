(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashBoardCtrl', ['$rootScope','$scope', 'CrudService', 'FlashService', '$location', 'AdminService', '$cookieStore','$http',
            function ($rootScope,$scope, CrudService, FlashService, $location, AdminService, $cookieStore,$http) {
                $scope.loggedUser='';
                $scope.loggedUser = $rootScope.globals.currentUser.username;
                $scope.loggedInBranchCode = $cookieStore.get('globals').currentUser.otherDetails.branch;

                $scope.getBranchDesc = function (branchCode) {
                    return AdminService.getBranchDesc(branchCode);
                };

                $scope.getCourseDesc = function (courseCode) {
                    return AdminService.getCourseDesc(courseCode);
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

                (function (){
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

                    AdminService.getCourses(function (data) {
                        $scope.courses = data
                    });
                };


                
                $scope.calcEnrol= function () {
                    var searchCriteria={};
                    searchCriteria.durationFrom=new Date().setHours(0,0,0,0);
                    $http.post('/student/search', searchCriteria).then(function(res) {
                        $scope.enrolled  = res.data;
                    })
                };

                $scope.totalIncome=function () {
                    var searchCriteria={};
                    searchCriteria.durationFrom=new Date().setHours(0,0,0,0);
                    searchCriteria.durationTo=new Date().setHours(23,59,59,59);
                    $http.post('/payment/search', searchCriteria).then(function(res) {
                        var datas = res.data;
                        var total=0;
                        _.forEach(datas, function (data) {
                            total = total + data.paymentInfo.amount;
                        });
                        $scope.totalPayment=total;
                    })
                }
                $scope.init();

            }])
        .controller("StudentDashboardCtrl",["$scope","AdminService","$http",
             function ($scope,AdminService,$http) {
                 $scope.init= function () {


                     AdminService.getUsers(function (res) {
                         $scope.users = res;
                     })

                     AdminService.getMarketingEmployees(function (res) {
                         $scope.marketingEmployees = res;
                     })
                 }
                 $scope.init();
                 var searchCriteria={};
                 searchCriteria.durationFrom=new Date().setHours(0,0,0,0);
                 $http.post('/student/search', searchCriteria).then(function(res) {
                      var studs  = res.data;
                     $scope.linkedUsers=[];
                     $scope.linkedUsers.marketingEmp=[];
                     $scope.linkedUsers.marketingEmp.studNumber=0;
                     $scope.linkedUsers.studNumber=0;
                     var flag=0;

                     _.forEach(studs, function (stud) {
                         if ($scope.linkedUsers.length==0){
                             $scope.linkedUsers.push({
                                 studNumber: $scope.linkedUsers.studNumber+1,
                                 name : AdminService.getLinkedUserName(stud.marketingEmployeeCode),
                                 marketingEmp:  $scope.linkedUsers.marketingEmp.push({
                                     name : AdminService.getMarketingEmployeeName(stud.marketingEmployeeCode),
                                     studNumber : $scope.linkedUsers.marketingEmp.studNumber+1
                                 })
                             })
                         }
                        else {
                             for (var i = 0; i < $scope.linkedUsers.length; i++) {
                                 if ($scope.linkedUsers[i].name == AdminService.getLinkedUserName(stud.marketingEmployeeCode)) {
                                     flag=1;
                                     break;
                                 }

                             }
                             if(flag==1){
                                 $scope.linkedUsers[i].studNumber += 1;
                                 marketingEmp:  $scope.linkedUsers[i].marketingEmp.push({
                                     name : AdminService.getMarketingEmployeeName(stud.marketingEmployeeCode),
                                     studNumber : $scope.linkedUsers.marketingEmp.studNumber+1
                                 })
                             }
                            else{
                                 $scope.linkedUsers.push({
                                     studNumber: $scope.linkedUsers.studNumber + 1,
                                     name: AdminService.getLinkedUserName(stud.marketingEmployeeCode),
                                     marketingEmp: $scope.linkedUsers.marketingEmp.push({
                                         name: AdminService.getMarketingEmployeeName(stud.marketingEmployeeCode),
                                         studNumber: $scope.linkedUsers.marketingEmp.studNumber + 1
                                     })
                                 })
                             }
                             flag=0;
                         }



                     })


                 })
        }])
        .controller("IncomeDashboardCtrl",["$scope","AdminService","$http","$state",
            function ($scope,AdminService,$http,$state) {
                $scope.changeLocation= function (branchName) {
                    $state.go('home.payment-list',{branch: branchName});
                }

               $scope.init= function () {
                   $scope.incomeBranches=[];
                   $scope.incomeBranches.amount=0;
                   $scope.incomeBranches.branchName='';

                   var searchCriteria = {};
                   searchCriteria.durationFrom = new Date().setHours(0, 0, 0, 0);
                   searchCriteria.durationTo = new Date().setHours(23, 59, 59, 59);
                   $http.post('/payment/search', searchCriteria).then(function (res) {
                       var datas = res.data;
                       _.forEach(datas, function (data) {
                           if( $scope.incomeBranches.length==0) {
                               $scope.incomeBranches.push({
                                   amount: $scope.incomeBranches.amount + data.paymentInfo.amount,
                                   branchName: data.branchName
                               })
                           }
                           else {
                               for (var i = 0; i < $scope.incomeBranches.length; i++) {
                                   if ($scope.incomeBranches[i].branchName == data.branchName) {
                                       $scope.incomeBranches[i].amount = $scope.incomeBranches[i].amount + data.paymentInfo.amount;
                                       break;
                                   }


                                   else {
                                       $scope.incomeBranches.push({
                                           amount: $scope.incomeBranches.amount + data.paymentInfo.amount,
                                           branchName: data.branchName

                                       })
                                       break;
                                   }
                               }
                           }
                       })
                   })
                   
               }
                $scope.init();

            
            }]);
})();