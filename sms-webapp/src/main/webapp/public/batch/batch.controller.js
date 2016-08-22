/**
 * Created by Assaycr-04 on 8/20/2016.
 */

(function () {
    'use strict';

    angular
        .module('Batch')
        .controller('BatchListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout', 'AdminService',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout,  AdminService) {
                $scope.updateBatch = function (batchId) {
                    $state.go('home.batch-update', {id: batchId});
                };

                $scope.getCourseName=AdminService.getCourseDesc;

                $scope.deleteBatch = function(id) {
                    CrudService.batchService.Delete(id).then(function(){
                        window.scrollTo(0,0);
                        FlashService.Success("Successfully Deleted !!", false);
                        $scope.tableParams.reload();
                    });
                };

                $scope.createNewBatch = function () {
                    $state.go('home.batch-creation');
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
                        CrudService.batchService.GetAll().then(function (data) {
                            if (data.message) {
                                $scope.batches = [];
                                FlashService.Error(data.message)
                            } else {
                                $scope.batches = data;
                            }
                            $timeout(function () {
                                params.total($scope.batches.length);
                                $defer.resolve($scope.batches);
                            }, 10);
                        }, function() {
                            $scope.batches = []
                            $timeout(function() {
                                params.total($scope.batches.length);
                                $defer.resolve($scope.batches);
                            }, 10);


                        })
                    }
                });
            }])
        .controller('BatchUpdateCtrl', ['$scope', '$stateParams', 'CrudService', 'FlashService', '$state','AdminService',
            function ($scope, $stateParams, CrudService, FlashService, $state,AdminService) {

                $scope.updateBatch = function () {
                    CrudService.batchService.Update($scope.batch).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Modified !!", true);
                        $state.go('home.batch-list');
                    });

                };

                $scope.cancel = function () {
                    $state.go('home.batch-list');
                };

                $scope.loadBatch = function () {
                    CrudService.batchService.GetById($stateParams.id).then(function (res) {
                        $scope.batch = res
                        $scope.batch.durationFrom= new Date(res.durationFrom);
                        $scope.batch.durationTo= new Date(res.durationTo);
                    })

                    AdminService.getCourses(function (data) {
                        $scope.courses=data;
                    })
                }



                $scope.loadBatch();
            }])
        .controller('BatchCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state','AdminService',
            function ($scope, CrudService, FlashService, $state,AdminService) {

                $scope.createNewBatch = function () {
                    CrudService.batchService.Create($scope.batch).then(function () {
                        window.scrollTo(0,0);
                        FlashService.Success("Successfuly Inserted !!", true);
                        $state.go('home.batch-list');
                    });
                }

                AdminService.getCourses(function (data) {
                    
                        $scope.courses=data;
                })

            }]);


})();
