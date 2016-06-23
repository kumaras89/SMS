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
            getCourses(function(){})
            getMarketingEmployees(function () {
            })
            getSchemes(function(){})
        }

        function getMarketingEmployeeName(marketingEmployeeCode) {
            var marketingEmployees = StorageService.getTrustedStoarage('/marketingEmployee');
            var emp =  _.find(marketingEmployees, function(marketingEmployee) {
                return marketingEmployee.code == marketingEmployeeCode
            })
            return emp != undefined ? emp.name : "";
        }

        function getMarketingEmployeeCode(marketingEmployeeName) {
            var marketingEmployees = StorageService.getTrustedStoarage('/marketingEmployee');
            var emp = _.find(marketingEmployees, function(marketingEmployee) {
                return marketingEmployee.name == marketingEmployeeName
            })
            return emp != undefined ? emp.code : "";
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
            var fees =  _.find(feesParticulars, function(feesParticular) {
                return feesParticular.code == feesParticularCode
            })
            return fees != undefined ? fees.name : "";
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
            var scheme =  _.find(schemes, function(scheme) {
                return scheme.name == schemeName
            })
            return scheme != undefined ? scheme.code : "";
        }

        function getSchemeDesc(schemeCode) {
            var schemes = StorageService.getTrustedStoarage('/scheme');
            var scheme =  _.find(schemes, function(scheme) {
                return scheme.code == schemeCode
            })
            return scheme != undefined ? scheme.name : "";
        }

        function getSchemeFeesInfo(schemeCode) {
            var schemes = StorageService.getTrustedStoarage('/scheme');
            var scheme =  _.find(schemes, function(scheme) {
                return scheme.code == schemeCode
            }).feesInfos
            return scheme != undefined ? scheme.feesInfos : "";
        }

        function getCourses(success) {
            StorageService.getFromStoarage('/course', function(data) {
                success(data);
            });
        }

        function getCourseCode(courseName) {
            var courses = StorageService.getTrustedStoarage('/course');
            var course =  _.find(courses, function(course) {
                return course.name == courseName
            })
            return course != undefined ? course.code : "";
        }

        function getCourseDesc(courseCode) {
            var courses = StorageService.getTrustedStoarage('/course');
            var course =  _.find(courses, function(course) {
                return course.code == courseCode
            })
            return course != undefined ? course.name : "";
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
            var branch =  _.find(branches, function(branch) {
                return branch.name == branchdesc
            }).code
            return branch != undefined ? branch.code : "";
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
