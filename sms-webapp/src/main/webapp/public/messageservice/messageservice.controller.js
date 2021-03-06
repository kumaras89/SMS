(function () {
    'use strict';

    angular
        .module('MessageService')
        .controller('MessageServiceListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
            function ($scope, CrudService, FlashService, $state,  ngTableParams, $timeout) {
                $scope.createMessage = function () {
                    $state.go('home.messageservice-creation');
                }
                $scope.tableParams = new ngTableParams({
                    page: 1,            // show first pagez
                    count: 10,          // count per page
                    sorting: {
                        name: 'asc'     // initial sorting
                    }
                }, {
                    total: 0,           // length of data
                    getData: function ($defer, params) {
                        CrudService.messageService.GetAll().then(function (data) {
                            if (data.message) {
                                $scope.entities = [];
                                FlashService.Error(data.message)
                            } else {
                                $scope.entities = data;
                            }
                            $timeout(function () {
                                params.total($scope.entities.length);
                                $defer.resolve($scope.entities);
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

            }])
        .controller('MessageServiceCreationCtrl', ['$scope', 'CrudService', 'FlashService', '$state',
            function ($scope, CrudService, FlashService, $state) {

                var text = document.getElementById("message").value;
                text = text.replace(message+" "+" "+" "+" ");

                $scope.sendMessage = function () {
                    $scope.messageService.status = 'SENT';
                    CrudService.messageService.Create($scope.messageService).then(function () {
                    window.scrollTo(0,0);
                    FlashService.Success("Message sent Successfuly !!", true);
                    $state.reload();
                    });
                }

            }]);
    
})();
