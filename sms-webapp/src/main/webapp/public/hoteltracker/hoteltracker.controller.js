(function () {
    'use strict';

    angular
        .module('HotelTracker')
        .controller('HotelTrackerCreationCtrl', ['$scope', 'FlashService', '$state', 'AdminService','CrudService',
            function ($scope, FlashService, $state, AdminService,CrudService) {

                $scope.branchChanged = function() {

                        return hoteltracker.branchCode == $scope.hoteltracker.branchCode;
                    }
                $scope.hotelChanged = function() {

                    return hoteltracker.hotelCode == $scope.hoteltracker.hotelCode;
                }
                
                $scope.init = function() {
                    AdminService.getHotels(function(data) {
                        $scope.hotels = data;
                    })
                    AdminService.getStudents(function(data) {
                        $scope.students = data;
                    })
                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs = data;
                    })
                    AdminService.getBranches(function(data) {
                        $scope.branches = data;
                    })
                }
                $scope.init();
                $scope.hotelTrackerCreate = function () {
                    var hoteltracker = $.extend(hoteltracker, $scope.hoteltracker);
                        hoteltracker.status='MAPPED';
                    CrudService.hotelTrackerService.Create(hoteltracker).then(function (res) {
                        if (res.message) {
                            FlashService.Error(res.message);
                        } else {
                            FlashService.Success("Successfuly Inserted !!", true);
                            $state.go('home.hoteltracker-list');
                        }
                    });
                }
            }])
        .controller('HotelTrackerListCtrl', ['$scope','$http', '$timeout','ngTableParams', 'FlashService', '$state', 'AdminService',
            function ($scope,$http, $timeout, ngTableParams, FlashService, $state, AdminService) {
                $scope.searchCriteria = {};
                $scope.getBranchDesc = AdminService.getBranchDesc;
                $scope.getStudentName= AdminService.getStudentName;
                $scope.getHotelName=AdminService.getHotelName;
                $scope.calculateDays = function (start,end) {
                    var start = moment(new Date(start));
                    var end = moment(new Date(end));

                    if( moment()<start) {
                      return   parseInt(
                            moment.duration(
                                moment(end).diff(
                                    moment(start)
                                )
                            ).asDays()
                        ) + 1+ ' Days';

                    }
                    else if(moment()<end){
                        return parseInt(
                            moment.duration(
                                moment(end).diff(
                                    moment()
                                )
                            ).asDays()
                        ) +1 + ' Days';
                    }
                    else return 'Date crosed'
                };

                $scope.init = function() {

                    AdminService.getConstants(function (data) {
                        $scope.statusList = data.hotelTrackerStatus;
                    });
                    AdminService.getHotels(function(data) {
                        $scope.hotels= _.pluck(data,"hotelName")

                    })
                    AdminService.getStudents(function(data) {
                        $scope.studentNames=data;
                        $scope.studentNames = _.pluck(data,"name")
                    })
                    AdminService.getHotelHrs(function(data) {
                        $scope.hrs= _.pluck(data,"name")
                    })
                    AdminService.getBranches(function(data) {
                        $scope.branches= _.pluck(data,"name")
                    })

                }
                $scope.init();

                $scope.changeLocation= function (id) {
                    $state.go('home.hoteltracker-detail', {id: id});
                }

                $scope.hotelTrackerCreate = function () {
                    $state.go('home.hoteltracker-creation');
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

                $scope.loadTracker = function () {
                    CrudService.hotelTrackerService.GetById($stateParams.id).then(function (res) {
                        $scope.hoteltracker = res;
                        $scope.hotel=AdminService.getHotelByCode($scope.hoteltracker.hotelCode);
                        $scope.branch=AdminService.getBranchDesc($scope.hoteltracker.branchCode);
                        $scope.hr=AdminService.getHrById($scope.hoteltracker.hotelHrId);
                        $scope.student=AdminService.getStudentByCode($scope.hoteltracker.studentCode);
                    });
                }
                $scope.calculateDays = function (start,end) {
                    var start = moment(new Date(start));
                    var end = moment(new Date(end));

                    if( moment()<start) {
                        return   parseInt(
                                moment.duration(
                                    moment(end).diff(
                                        moment(start)
                                    )
                                ).asDays()
                            ) + 1+ ' Days';

                    }
                    else if(moment()<end){
                        return parseInt(
                                moment.duration(
                                    moment(end).diff(
                                        moment()
                                    )
                                ).asDays()
                            ) +1 + ' Days';
                    }
                    else return 'Date crossed'
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
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.hoteltracker-list');
                    });
                };
                $scope.hotelChanged = function() {

                    return hoteltracker.hotelCode == $scope.hoteltracker.hotelCode;
                };
                $scope.init = function() {
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
                        $scope.studentName = AdminService.getStudentName(res.studentCode);
                        hoteltracker.durationFrom = new Date(res.durationFrom);
                        hoteltracker.hotelHrId= (AdminService.getHrById(res.hotelHrId)).name;
                        hoteltracker.durationTo = new Date(res.durationTo);
                        $scope.hoteltracker = hoteltracker;
                    })
                };

                $scope.loadHotelTracker();
            }]);

})();