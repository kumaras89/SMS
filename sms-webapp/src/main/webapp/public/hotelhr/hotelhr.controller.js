(function () {
    'use strict';

    angular
        .module('HotelHr')
        .controller('HotelHrListCtrl', ['$rootScope', '$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout', 'CrudService',
            function ($rootScope, $scope, FlashService, ngTableParams, $state, AdminService, $timeout, CrudService) {
                var logInUserDet = $rootScope.globals.currentUser.otherDetails;
                var branchCode = $rootScope.globals.currentUser.otherDetails.branch;


                $scope.createNewHr = function () {
                    $state.go('home.hotelhr-registration');
                };

                $scope.updateHotelHr = function (id) {
                    $state.go('home.hotelhr-update', {id: id});
                };

                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function ($defer, params) {
                        $scope.entities = []
                        CrudService.hotelHrService.GetAll().then(function (res) {
                            if (res.message) {
                                $scope.entities = []
                                FlashService.Error(res.message)
                            } else {

                                if (logInUserDet.role == 'SUPER_ADMIN') {
                                    $scope.entities = res;
                                }
                                else {
                                    _.forEach(res, function (hr) {
                                        if (hr.branchCode == branchCode) {
                                            $scope.entities.push({
                                                name: hr.name,
                                                hrCode: hr.hrCode,
                                                hotelCode: hr.hotelCode,
                                                phoneNumber:hr.phoneNumber
                                            })
                                        }
                                    })
                                }
                            }

                        $timeout(function () {
                            params.total($scope.entities.length);
                            $defer.resolve($scope.entities);
                        }, 10);
                    }, function() {
                        $scope.entities = []
                        $timeout(function () {
                            params.total($scope.entities.length);
                            $defer.resolve($scope.entities);
                        }, 10);
                    })
            }
});

$scope.getHotelName = AdminService.getHotelName


}])
.
controller('HotelHrRegistrationCtrl', ['$scope', 'FlashService', 'ngTableParams', '$state', 'AdminService', '$timeout', 'CrudService',
    function ($scope, FlashService, ngTableParams, $state, AdminService, $timeout, CrudService) {

        $scope.createNewHr = function () {
            CrudService.hotelHrService.Create($scope.hotelhr).then(function (res) {
                window.scrollTo(0, 0);
                if (res.message) {
                    FlashService.Error(res.message);
                } else {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $state.go('home.hotelhr-list');
                }
            });

        };
    }])
    .controller('HotelHrUpdateCtrl', ['$scope', '$stateParams', 'FlashService', 'CrudService', 'ngTableParams', '$state', 'AdminService',
        function ($scope, $stateParams, FlashService, CrudService, ngTableParams, $state, AdminService) {
            $scope.updateHotelHr = function () {
                CrudService.hotelHrService.Update($scope.hotelhr).then(function () {
                    window.scrollTo(0, 0);
                    FlashService.Success("Successfuly Modified !!", true);
                    $state.go('home.hotelhr-list');
                });
            };
            $scope.loadHotelHr = function () {
                CrudService.hotelHrService.GetById($stateParams.id).then(function (res) {
                    var hotelhr = res;
                    $scope.hotelhr = hotelhr;
                })
            }
            $scope.loadHotelHr();
        }]);

})
();







