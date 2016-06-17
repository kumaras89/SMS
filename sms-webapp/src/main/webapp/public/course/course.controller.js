(function () {
    'use strict';

    angular
        .module('Course')
        .controller('CourseListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout) {
                $scope.editCourse = function (userId) {
                    $state.go('home.course-detail', {id: userId});
                };

                $scope.deleteCourse = function (id) {
                    CrudService.courseService.Delete(id).then(function () {
                        FlashService.Success('Successfully Deleted');
                    });

                };

                $scope.createNewCourse = function () {
                    $state.go('home.course-creation');
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
                        CrudService.courseService.GetAll().then(function (data) {
                            if (data.message) {
                                $scope.courses = [];
                                FlashService.Error(data.message)
                            } else {
                                $timeout(function () {
                                    params.total(data.length);
                                    $defer.resolve(data);
                                }, 10);

                            }
                        }, function () {
                            $scope.courses = []
                        })
                    }
                });
            }])
        .controller('CourseDetailCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state',
            function ($scope, $stateParams, CrudService, FlashService, $state) {

                $scope.updateCourse = function () {
                    CrudService.courseService.Update($scope.course).then(function () {
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.course-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.course-list');
                };

                $scope.loadCourse = function () {
                    CrudService.courseService.GetById($stateParams.id).then(function (res) {
                        $scope.course = res
                    })
                }

                $scope.loadCourse();
            }])
        .controller('CourseCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state',
            function ($scope, CrudService, FlashService, $state) {

                $scope.createNewCourse = function () {
                    CrudService.courseService.Create($scope.course).then(function () {
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.course-list');
                    });
                }
            }]);
})();
