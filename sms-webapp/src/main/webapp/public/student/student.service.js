(function () {
    'use strict';

    angular
        .module('app')
        .factory('StudentService', StudentService);

    StudentService.$inject = ['CrudService'];
    function StudentService(CrudService) {

        var service = {};

        service.getReligions = getReligions
        service.getCaste = getCaste
        service.getMaritalStatus = getMaritalStatus
        service.getGender = getGender
        service.getRatings = getRatings
        service.getRelations = getRelations

        return service;

        function getReligions(success) {
            success(['HINDU', 'MUSLIM', 'CHRISTIAN', 'SIKH', 'PARSI', 'JAIN', 'BUDDHIST', 'OTHERS']);
        }

        function getCaste(success) {
            success(['SC', 'ST', 'OBC', 'OC', 'MBC', 'BC', 'OTHERS']);
        }

        function getMaritalStatus(success) {
            success(['MARRIED', 'SINGLE']);
        }

        function getGender(success) {
            success(['MALE', 'FEMALE', 'OTHERS']);
        }

        function getRatings(success) {
            success(['EXCELLENT', 'AVERAGE', 'BELOW_AVERAGE']);
        }

        function getRelations(success) {
            success(['FATHER', 'MOTHER', 'BROTHER', 'SISTER', 'GRAND_FATHER', 'GRAND_MOTHER', 'GUARDIAN']);
        }
      }
})();
