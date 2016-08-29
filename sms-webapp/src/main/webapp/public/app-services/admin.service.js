(function () {
    'use strict';

    angular
        .module('app')
        .factory('AdminService', AdminService)
        .factory('AdminServiceProvider', AdminServiceProvider);

    AdminServiceProvider.$inject = ['StorageService'];

    AdminService.$inject = ['AdminServiceProvider'];

    function AdminService(AdminServiceProvider) {
        return AdminServiceProvider.initService();

    }

    function AdminServiceProvider(StorageService) {

        var service = {};

        service.getRoles = getRoles
        service.getBranches = getBranches
        service.getSchemes = getSchemes
        service.getFeesParticularDesc = getFeesParticularDesc
        service.getSchemeCode = getSchemeCode
        service.getSchemeDesc = getSchemeDesc
        service.getCourses = getCourses
        service.getCourseCode = getCourseCode
        service.getCourseDesc = getCourseDesc
        service.getCourseDescByBatchName = getCourseDescByBatchName
        service.getBranchCode = getBranchCode
        service.getBranchDesc = getBranchDesc
        service.getConstants = getConstants
        service.getMarketingEmployees = getMarketingEmployees
        service.getScholarshipEnrollment = getScholarshipEnrollment
        service.getMessageService = getMessageService
        service.getMarketingEmployeeCode = getMarketingEmployeeCode
        service.getMarketingEmployeeName = getMarketingEmployeeName
        service.getUsers=getUsers
        service.getSchemeFeesInfo = getSchemeFeesInfo
        service.getYearOfPassing = getYearOfPassing
        service.getHotels=getHotels
        service.getHotelHrs=getHotelHrs
        service.getHotelCode=getHotelCode
        service.getHotelName = getHotelName
        service.getHotelTrackers = getHotelTrackers
        service.getHotelByCode = getHotelByCode
        service.getHrById = getHrById
        service.getBranchById = getBranchById
        service.getBatches = getBatches

        return {
            serviceImpl: null,
            initService: function () {
                if (this.serviceImpl == null) {
                    initService()
                    this.serviceImpl = service;
                }
                return this.serviceImpl;
            }
        };

        function initService() {
            getBranches(function(){})
            getRoles(function(){})
            getFeesParticulars(function(){})
            getConstants(function(){})
            getCourses(function(){})
            getBatches(function () {})
            getMarketingEmployees(function () {})
            getScholarshipEnrollment(function () {})
            getSchemes(function(){})
            getUsers(function () {})
            getHotels(function () {})
            getHotelCode(function () {})
            getHotelHrs(function () {})
            getMessageService(function () {})
            getHotelName(function () {})
            getHotelTrackers(function () {})
            getHotelByCode(function () {})
            getHrById=getHrById(function () {})
            getBranchById=getBranchById(function (){})

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
            })
            return scheme != undefined ? scheme.feesInfos : [];
        }

        function getCourses(success) {
            StorageService.getFromStoarage('/course', function(data) {
                success(data);
            });
        }
        function getScholarshipEnrollment(success) {
            StorageService.getFromStoarage('/scholarshipEnrollment', function(data) {
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
            })
            return branch != undefined ? branch.code : "";
        }

        function getBranchDesc(branchCode) {
            var branches = getBranches0();
            var branch =  _.find(branches, function(branch) {
                return branch.code == branchCode
            })
            return branch != undefined ? branch.name : "";
        }
        function getUsers(success) {
            StorageService.getFromStoarage('/user', function(data) {
                success(data);
            });
        }
        function getMessageService(success) {
            StorageService.getFromStoarage('/messageService', function(data) {
                success(data);
            });

        }
        function getHotels(success) {
            StorageService.getFromStoarage('/hotel', function(data) {
                success(data);
            });
        }
        function getHotelHrs(success) {
            StorageService.getFromStoarage('/hotelhr', function(data) {
                success(data);
            });

        }

        function getHotelCode(hotelName) {
            var hotels = StorageService.getTrustedStoarage('/hotel');
            var hotel = _.find(hotels, function (hotel) {
                return hotel.hotelName == hotelName
            })
            return hotel != undefined ? hotel.hotelCode : "";
        }

        function getHotelName(hotelCode) {
            var hotels = StorageService.getTrustedStoarage('/hotel');
            var emp =  _.find(hotels, function(hotel) {
                return hotel.hotelCode == hotelCode
            })
            return emp != undefined ? emp.hotelName : "";
        }

        function getHotelTrackers(success) {
            StorageService.getTrustedStoarage('/hoteltracker', function(data) {
                success(data);
            });

        }

        function getHotelByCode(hotelCode) {
            var hotels = StorageService.getTrustedStoarage('/hotel');
            var emp =  _.find(hotels, function(hotel) {
                return hotel.hotelCode == hotelCode
            })
            return emp;
        }

        function getHrById(hrId) {
            var hrs = StorageService.getTrustedStoarage('/hotelhr');
            var emp =  _.find(hrs, function(hr) {
                return hr.id == hrId
            })
            return emp;
        }
        function getBranchById(branchId) {
            var branches = StorageService.getTrustedStoarage('/branch');
            var emp =  _.find(branches, function(branch) {
                return branch.id == branchId
            })
            return emp;
        }
        

        function getBatches(success) {
            StorageService.getFromStoarage('/batch', function (data) {
                success(data);
            });

        }

        function getCourseDescByBatchName(batchName) {
            var batches = StorageService.getTrustedStoarage('/batch');
            var bat = _.find(batches, function (batch) {
                return batch.name == batchName
            })
            return getCourseDesc(bat.courseCode);
        }


    }
})();
