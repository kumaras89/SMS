(function () {
    'use strict';

    angular
        .module('app')
        .factory('AdminService', AdminService);

    AdminService.$inject = ['StorageService'];
    function AdminService(StorageService) {

        var service = {};
        
        initService();

        service.getRoles = getRoles
        service.getBranches = getBranches
        service.getBranchCode = getBranchCode
        service.getBranchDesc = getBranchDesc

        return service;
        
        function initService() {
            getBranches(function(){})
            getRoles(function(){})
        }

         function getBranches(success) {
             StorageService.getFromStoarage('branches', '/branch', function(data) {
                 success(data);
             });
         }

        function getRoles(success) {
            StorageService.getFromStoarage('roles', '/role', function(data) {
                success(data);
            });
        }

        function getBranches0(){
            return StorageService.getTrustedStoarage('branches');
        }

        function getBranchCode(branchdesc) {
            var branches = getBranches0();
            return _.find(branches, function(branch) {
                return branch.name == branchdesc
            }).code
        }

        function getBranchDesc(branchCode) {
            var branches = getBranches0();
            return _.find(branches, function(branch) {
                return branch.code == branchCode
            }).name
        }

    }

})();
