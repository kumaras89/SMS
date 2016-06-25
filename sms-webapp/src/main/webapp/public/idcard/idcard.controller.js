(function () {
    'use strict';

    angular
        .module('IDCard')
        .controller('IDCardListCtrl', ['$scope', 'CrudService', 'FlashService', '$state', 'ngTableParams', '$timeout',
            function ($scope, CrudService, FlashService, $state, ngTableParams, $timeout) {

                $scope.search = function () {

                    $scope.tableParams = new ngTableParams({
                        page: 1,            // show first pagez
                        count: 10,          // count per page
                        sorting: {
                            name: 'asc'     // initial sorting
                        }
                    }, {
                        total: 0,           // length of data
                        getData: function ($defer, params) {
                            CrudService.idcardService.Search($scope.idcard).then(function (data) {
                                if (data.message) {
                                    FlashService.Error(data.message)
                                } else {
                                    $timeout(function () {
                                        params.total(data.length);
                                        $defer.resolve(data);
                                    }, 10);

                                }
                            })
                        }
                    })
                };
            }]);
})();
