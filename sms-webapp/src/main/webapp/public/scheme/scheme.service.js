(function () {
    'use strict';

    angular
        .module('app')
        .factory('SchemeService', SchemeService);

    SchemeService.$inject = ['CrudService'];
    function SchemeService(CrudService) {

        var service = {};

        service.getFeesParticulars = getFeesParticulars

        return service;

        function getFeesParticulars(success) {
            CrudService.feesParticularService.GetAll().then(function (res) {
                if (res.message) {
                    success([])
                } else {
                    success(res)
                }
            }, function () {
                success([])
            })
        }
    }

})();
