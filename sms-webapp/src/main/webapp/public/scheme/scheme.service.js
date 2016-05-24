(function () {
    'use strict';

    angular
        .module('app')
        .factory('SchemeService', SchemeService);

    SchemeService.$inject = ['CrudService'];
    function SchemeService(CrudService) {

        var service = {};

        service.getFeesCategories = getFeesCategories

        return service;

        function getFeesCategories(success) {
            success(['Admission Fees',
                     'Tution Fees',
                     'Registration Fees',
                     'Entrance Fees',
                     'Uniform Fees',
                     'Tamil/English Typing Fees',
                     'Pratical/Lab Fees',
                     'Book/Record Note Fees',
                     'Exam Note Fees',
                     'Hostel/Mess Fees',
                     'Bus Fees',
                     'Breakage Fees',
                     'Other Fees']);
        }
    }

})();
