(function () {
    'use strict';

    angular
        .module('MarketingEmployee')
        .controller('MarketingEmployeeListCtrl', ['$scope', 'CrudService', 'FlashService', '$location',
            function ($scope, CrudService, FlashService, $location) {
                $scope.editMarketingEmployee = function (userId) {
                    $location.path('/marketingemployee-detail/' + userId);
                };

                $scope.deleteMarketingEmployee = function (id) {
                    CrudService.marketingEmployeeService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                        $scope.loadMarketingEmployees();
                    });

                };

                $scope.createNewMarketingEmployee = function () {
                    $location.path('/marketingemployee-creation');
                };

                $scope.loadMarketingEmployees = function () {
                    CrudService.marketingEmployeeService.GetAll().then(function (res) {
                        if (res.message) {
                            $scope.marketingEmployees = []
                            FlashService.Error(res.message)
                        } else {
                            $scope.marketingEmployees = res
                        }
                    }, function () {
                        $scope.marketingEmployees = []
                    })
                }

                $scope.loadMarketingEmployees()


            }])
        .controller('MarketingEmployeeDetailCtrl', ['$scope', '$routeParams', 'CrudService', 'FlashService', '$location', 'AdminService',
            function ($scope, $routeParams, CrudService, FlashService, $location, AdminService) {

                $scope.updateMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.Update($scope.marketingEmployee).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $location.path('/marketingemployee-list');
                    });

                };

                $scope.cancel = function () {
                    $location.path('/marketingemployee-list');
                };

                $scope.loadMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.GetById($routeParams.id).then(function (res) {
                        $scope.marketingEmployee = res
                    })
                }

                $scope.init = function () {
                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });

                    $scope.loadMarketingEmployee();
                }

                $scope.init();

            }])
        .controller('MarketingEmployeeCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$location', 'AdminService',
            function ($scope, CrudService, FlashService, $location, AdminService) {

                $scope.init = function () {

                    AdminService.getConstants(function (data) {
                        $scope.commonAttributes = data;
                    });
                }

                $scope.init();


                $scope.createNewMarketingEmployee = function () {
                    CrudService.marketingEmployeeService.Create($scope.marketingEmployee).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $location.path('/marketingemployee-list');
                    });
                }
            }]);

})();
