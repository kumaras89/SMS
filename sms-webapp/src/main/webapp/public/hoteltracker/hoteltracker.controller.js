(function () {
    'use strict';

    angular
        .module('HotelTracker')
        .controller('HotelTrackerCreationCtrl', ['$rootScope','$scope', 'FlashService', '$state', 'AdminService','CrudService','$http',
            function ($rootScope,$scope, FlashService, $state, AdminService,CrudService,$http) {
                var logInUserDet = $rootScope.globals.currentUser.otherDetails;
                var branchCode = $rootScope.globals.currentUser.otherDetails.branch;

                $scope.hoteltracker = {};
                $scope.branchname = '';
                $scope.durations= [1,2,3,4,5,6,7,8,9,10,11,12];
                $scope.branchChanged = function () {
                    var branchName = AdminService.getBranchDesc($scope.hoteltracker.branchCode);
                    return hoteltracker.branchCode == $scope.hoteltracker.branchCode;
                }
                $scope.durationChanged=function () {
                    var CurrentDate = moment($scope.hoteltracker.durationFrom);
                   return $scope.hoteltracker.durationTo=new Date(CurrentDate.add($scope.duration, 'months'));
                }
                
                $scope.getStudents= function (branchName) {
                    $scope.students={};
                    var searchCriteria = $.extend(searchCriteria, $scope.searchCriteria);
                    searchCriteria.branchName=branchName;

                    $http.post('/student/search', searchCriteria).then(function(res) {
                        $scope.students=res.data;
                    })
                }
                
                
                
                $scope.init = function() {
                    $scope.hoteltracker.durationFrom = new Date();
                    AdminService.getHotels(function(data) {
                        $scope.hotels = data;
                    })

                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs = data;
                    })
                    AdminService.getBranches(function(data) {
                        $scope.branches=[];
                        if (logInUserDet.role == 'SUPER_ADMIN') {
                            $scope.branches = data;
                        }
                        else {
                            var branch=AdminService.getBranchByCode(branchCode);
                            $scope.branches.push({
                                name:branch.name,
                                code: branch.code
                            })
                        }


                    })
                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });

                }
                $scope.init();
                $scope.hotelTrackerCreate = function () {
                    var hoteltracker = $.extend(hoteltracker, $scope.hoteltracker);
                        hoteltracker.status='MAPPED';
                    CrudService.hotelTrackerService.Create(hoteltracker).then(function (res) {
                        window.scrollTo(0,0);
                        if (res.message) {
                            FlashService.Error(res.message,false);
                        } else {
                            FlashService.Success("Successfuly Inserted !!", true);
                            $state.go('home.hoteltracker-list');
                        }
                    });
                }
            }])
        .controller('HotelTrackerListCtrl', ['$rootScope','$scope','$http', '$timeout','ngTableParams', 'FlashService', '$state', 'AdminService',
            function ($rootScope,$scope,$http, $timeout, ngTableParams, FlashService, $state, AdminService) {
                var logInUserDet = $rootScope.globals.currentUser.otherDetails.role;
                var branch = $rootScope.globals.currentUser.otherDetails.branch;

                $scope.searchCriteria = {};
                $scope.getBranchDesc = AdminService.getBranchDesc;
                $scope.getHotelName=AdminService.getHotelName;
                $scope.calculateDays = function (start,end) {
                    var start = moment(new Date(start));
                    var end = moment(new Date(end));

                    if( moment().isBefore(start)) {
                      return   parseInt(
                            moment.duration(
                                moment(end).diff(
                                    moment(start)
                                )
                            ).asDays()
                        ) + 1+ ' Days';
                    }

                    else if( end.isSame(moment(), 'day')){
                        return '1 Day';
                    }
                    else if(moment().isBefore(end)){
                        return parseInt(
                            moment.duration(
                                moment(end).diff(
                                    moment()
                                )
                            ).asDays()
                        ) +2 + ' Days';
                    }
                    else return 'Date crosed'
                };

                $scope.init = function() {

                    AdminService.getConstants(function (data) {
                        $scope.statusList = data.hotelTrackerStatus;
                    });
                    AdminService.getHotels(function(data) {
                        $scope.hotels=[]
                        if (logInUserDet == 'SUPER_ADMIN')
                            $scope.hotels= data;
                        else {
                            _.forEach(data, function (hotel) {
                                if (hotel.branchCode == branch) {
                                    $scope.hotels.push({
                                        hotelName: hotel.hotelName,
                                    })
                                }
                            })
                        }

                      
                    })
                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs= _.pluck(data,"name")
                    })
                    AdminService.getBranches(function(data) {
                        $scope.branches= _.pluck(data,"name")
                    })
                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });
                }
                $scope.init();

                $scope.changeLocation= function (id) {
                    $state.go('home.hoteltracker-detail', {id: id});
                }

                $scope.hotelTrackerCreate = function () {
                    $state.go('home.hoteltracker-creation');
                }

                $scope.search = function () {
                    if($scope.searchCriteria.status===""){
                        $scope.searchCriteria={} ;                   }
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
                                $http.post('/hoteltracker/search', $scope.searchCriteria).then(function(res) {
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

            }])
        .controller('HotelTrackerDetailCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state','$stateParams', '$location', 'AdminService','CrudService',
            function ($scope, FlashService, ngTableParams, $state, $stateParams,$location, AdminService, CrudService) {

                $scope.hotel={};
                $scope.hr={};
                $scope.loadTracker = function () {
                    CrudService.hotelTrackerService.GetById($stateParams.id).then(function (res) {
                        $scope.hoteltracker = res;
                        $scope.branch=AdminService.getBranchDesc($scope.hoteltracker.branchCode);
                        var hotell =AdminService.getHotelByCode($scope.hoteltracker.hotelCode);
                        $scope.hotel.name= hotell.hotelName;
                        $scope.hotel.phoneNumber= hotell.phoneNumber;
                        var hrr=AdminService.getHrById($scope.hoteltracker.hotelHrId);
                        $scope.hr.name= hrr.name;
                        $scope.hr.phoneNumber= hrr.phoneNumber;
                    });
                }

                $scope.calculateDays = function (start,end) {
                    var start = moment(new Date(start));
                    var end = moment(new Date(end));

                    if( moment().isBefore(start)) {
                        return   parseInt(
                                moment.duration(
                                    moment(end).diff(
                                        moment(start)
                                    )
                                ).asDays()
                            ) + 1+ ' Days';
                    }

                    else if( end.isSame(moment(), 'day')){
                        return '1 Day';
                    }
                    else if(moment().isBefore(end)){
                        return parseInt(
                                moment.duration(
                                    moment(end).diff(
                                        moment()
                                    )
                                ).asDays()
                            ) +2 + ' Days';
                    }
                    else return 'Date crosed'
                };

                $scope.loadTracker();
                $scope.updateTracker= function (id) {
                    $state.go('home.hoteltracker-update', {id: id});
                }

            }])
        
        .controller('HotelTrackerUpdateCtrl', ['$scope','$stateParams', 'FlashService','CrudService', 'ngTableParams', '$state', 'AdminService',
            function ($scope,$stateParams, FlashService,CrudService, ngTableParams, $state, AdminService) {
                $scope.updateTracker = function () {
                    CrudService.hotelTrackerService.Update($scope.hoteltracker).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.hoteltracker-list');
                    });
                };
                $scope.hotelChanged = function() {

                    return hoteltracker.hotelCode == $scope.hoteltracker.hotelCode;
                };
                $scope.init = function() {
                    AdminService.getBranches(function(data) {
                        $scope.branches = data;
                        $scope.branchNames = _.pluck(data, "name");
                    })
                    AdminService.getHotels(function(data) {
                        $scope.hotels = data;
                    })

                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs = data;
                    })
                    AdminService.getConstants(function (data) {
                        $scope.statusList = data.hotelTrackerStatus;
                    });
                };
                $scope.init();

                $scope.loadHotelTracker = function() {
                    CrudService.hotelTrackerService.GetById($stateParams.id).then(function (res) {
                        var hoteltracker = res;
                        $scope.branchName=AdminService.getBranchDesc(res.branchCode)
                        hoteltracker.durationFrom = new Date(res.durationFrom);
                        $scope.hrName = AdminService.getHrById(res.hotelHrId).name;
                        hoteltracker.durationTo = new Date(res.durationTo);
                        hoteltracker.hotel=AdminService.getHotelByCode(res.hotelCode);
                        $scope.hoteltracker = hoteltracker;
                    })
                };

                $scope.loadHotelTracker();
            }]);

})();