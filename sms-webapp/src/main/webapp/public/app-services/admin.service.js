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
        service.getSchemes = getSchemes
        service.getFeesParticularDesc = getFeesParticularDesc
        service.getSchemeCode = getSchemeCode
        service.getSchemeDesc = getSchemeDesc
        service.getCourses = getCourses
        service.getCourseCode = getCourseCode
        service.getCourseDesc = getCourseDesc
        service.getBranchCode = getBranchCode
        service.getBranchDesc = getBranchDesc
        service.getConstants = getConstants
        service.getMarketingEmployees = getMarketingEmployees
        service.getMarketingEmployeeCode = getMarketingEmployeeCode
        service.getMarketingEmployeeName = getMarketingEmployeeName
        service.getSchemeFeesInfo = getSchemeFeesInfo
        service.getYearOfPassing = getYearOfPassing

        return service;
        
        function initService() {
            getBranches(function(){})
            getRoles(function(){})
            getFeesParticulars(function(){})
            getConstants(function(){})
        }

        function getMarketingEmployeeName(marketingEmployeeCode) {
            var marketingEmployees = StorageService.getTrustedStoarage('/marketingEmployee');
            return _.find(marketingEmployees, function(marketingEmployee) {
                return marketingEmployee.code == marketingEmployeeCode
            }).name
        }

        function getMarketingEmployeeCode(marketingEmployeeName) {
            var marketingEmployees = StorageService.getTrustedStoarage('/marketingEmployee');
            return _.find(marketingEmployees, function(marketingEmployee) {
                return marketingEmployee.name == marketingEmployeeName
            }).code
        }

        function getMarketingEmployees(success) {
            StorageService.getFromStoarage('/marketingEmployee', function(data) {
                success(data);
            });
        }

        function getYearOfPassing(success){
            var thisYear = new Date().getFullYear();
            var years = [thisYear,thisYear - 1];
            return success(years);
        }

        function getConstants(success) {
            StorageService.getFromStoarage('/common', function(data) {
                success(data);
            });
        }

        function getFeesParticulars(success) {
            StorageService.getFromStoarage('/feesParticular', function(data) {
                success(data);
            });
        }

        function getFeesParticularDesc(feesParticularCode) {
            var feesParticulars = StorageService.getTrustedStoarage('/feesParticular');
            return _.find(feesParticulars, function(feesParticular) {
                return feesParticular.code == feesParticularCode
            }).name
        }

         function getBranches(success) {
             StorageService.getFromStoarage('/branch', function(data) {
                 success(data);
             });
         }

        function getSchemes(success) {
            StorageService.getFromStoarage('/scheme', function(data) {
                success(data);
            });
        }

        function getSchemeCode(schemeName) {
            var schemes = StorageService.getTrustedStoarage('/scheme');
            return _.find(schemes, function(scheme) {
                return scheme.name == schemeName
            }).code
        }

        function getSchemeDesc(schemeCode) {
            var schemes = StorageService.getTrustedStoarage('/scheme');
            return _.find(schemes, function(scheme) {
                return scheme.code == schemeCode
            }).name
        }

        function getSchemeFeesInfo(schemeCode) {
            var schemes = StorageService.getTrustedStoarage('/scheme');
            return _.find(schemes, function(scheme) {
                return scheme.code == schemeCode
            }).feesInfos
        }

        function getCourses(success) {
            StorageService.getFromStoarage('/course', function(data) {
                success(data);
            });
        }

        function getCourseCode(courseName) {
            var courses = StorageService.getTrustedStoarage('/course');
            return _.find(courses, function(course) {
                return course.name == courseName
            }).code
        }

        function getCourseDesc(courseCode) {
            var courses = StorageService.getTrustedStoarage('/course');
            return _.find(courses, function(course) {
                return course.code == courseCode
            }).name
        }

        function getRoles(success) {
            StorageService.getFromStoarage('/role', function(data) {
                success(data);
            });
        }

        function getBranches0(){
            return StorageService.getTrustedStoarage('/branch');
        }

        function getBranchCode(branchdesc) {
            var branches = getBranches0();
            return _.find(branches, function(branch) {
                return branch.name == branchdesc
            }).code
        }

        function getBranchDesc(branchCode) {
            var branches = getBranches0();
            var branch =  _.find(branches, function(branch) {
                return branch.code == branchCode
            })
            return branch != undefined ? branch.name : "";
        }

    }

})();
