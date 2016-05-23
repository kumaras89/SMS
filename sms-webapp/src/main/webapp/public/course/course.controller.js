(function () {
    'use strict';

    angular
        .module('Course')
        .controller('CourseListCtrl', ['$scope', 'CrudService', 'FlashService', '$location',
        function ($scope, CrudService, FlashService, $location) {
            $scope.editCourse = function (userId) {
                $location.path('/course-detail/' + userId);
            };

            $scope.deleteCourse = function (id) {
                CrudService.courseService.Delete(id).then(function() {
                    FlashService.Success('Successfully Deleted');
                    $scope.loadCourses();
                });

            };

            $scope.createNewCourse = function () {
                $location.path('/course-creation');
            };

            $scope.loadCourses = function () {
                CrudService.courseService.GetAll().then(function(res) {
                    if(res.message) {
                        $scope.courses = []
                        FlashService.Error(res.message)
                    } else {
                        $scope.courses = res
                    }
                }, function() {
                    $scope.courses = []
                })
            }

            $scope.loadCourses()



        }])
        .controller('CourseDetailCtrl', ['$scope', '$routeParams', 'CrudService','FlashService', '$location',
        function ($scope, $routeParams, CrudService,FlashService, $location) {

            $scope.updateCourse = function () {
                CrudService.courseService.Update($scope.course).then(function(){
                    FlashService.Success("Successfuly Modified !!", true);
                    $location.path('/course-list');
                });

            };

            $scope.cancel = function () {
                $location.path('/course-list');
            };

            $scope.loadCourse = function() {
                CrudService.courseService.GetById($routeParams.id).then(function(res) {
                    $scope.course = res
                })
            }

            $scope.loadCourse();
        }])
        .controller('CourseCreationCtrl', ['$scope', 'CrudService','FlashService', '$location',
        function ($scope, CrudService,FlashService, $location) {

            $scope.createNewCourse = function () {
                CrudService.courseService.Create($scope.course).then(function () {
                    FlashService.Success("Successfuly Inserted !!", true);
                    $location.path('/course-list');
                });

            }
        }]);

})();
