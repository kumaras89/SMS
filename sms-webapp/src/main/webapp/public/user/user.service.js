(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['CrudService'];
    function UserService(CrudService) {

        var service = {};

        service.getRoles = getRoles
        service.getBranches = getBranches
        service.getBranchCode = getBranchCode
        service.getBranchDesc = getBranchDesc

        return service;


        function getRoles(success) {
            success(['SUPER_ADMIN', 'BRANCH_ADMIN', 'BRANCH_CLERK']);
        }

        function getBranches(success) {
            CrudService.branchService.GetAll().then(function(res) {
                if(res.message) {
                    success([])
                } else {
                    success(res)
                }
            }, function() {
                success([])
            })
        }

        function getBranchCode(branches, branchdesc) {
            return _.find(branches, function(branch) {
                return branch.name == branchdesc
            }).code
        }

        function getBranchDesc(branches, branchCode) {
            return _.find(branches, function(branch) {
                return branch.code == branchCode
            }).name
        }
        
    }

})();
