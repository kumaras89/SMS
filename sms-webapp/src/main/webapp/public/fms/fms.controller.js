(function () {
    'use strict';

    angular
        .module('FMS')
        .controller('FMSCtrl', ['$scope', '$http', '$routeParams', 'FlashService', '$location',
            function ($scope,$http, $routeParams, CrudService, FlashService, $location) {

                $scope.init = function(path, clearCachePaths) {
                    $scope.cateory = $routeParams.category;
                    $scope.uploaderid = $routeParams.uploaderid;
                    $http.get( '/document/doctypes'+ $scope.cateory).then(
                        function(res) {
                           $scope.doctypes = res;
                        }
                    );

                    $http.get( '/document/documents/'+ $scope.uploaderid).then(
                        function(res) {
                            $scope.documents = res;
                        }
                    );
                }

            }]);

})();
