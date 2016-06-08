(function () {
    'use strict';

    angular
        .module('app')
        .factory('CrudService', CrudService);

    CrudService.$inject = ['$http'];
    function CrudService($http) {

        var service = {};

        service.of = function(path) {
            return new Service(path)
        }

        service.branchService = service.of('branch')
        service.userService = service.of('user')
        service.courseService = service.of('course')
        service.schemeService = service.of('scheme')
        service.feesParticularService = service.of('feesParticular')
        service.studentService = service.of('student')
        service.marketingEmployeeService = service.of('marketingEmployee')

        return service;

        function Service(path) {
            this.path = path;
            this.GetAll =  function() {
                var res = $http.get('/'+this.path).then(handleSuccess, handleError('Error getting all '+this.path))
                return res;
            }

            this.GetById = function(id) {
                return $http.get( '/'+this.path +'/'+ id).then(handleSuccess, handleError('Error getting '+this.path+' by '+this.path+' id'));
            }

            this.Create = function(data) {
                return $http.post('/'+this.path, data).then(handleSuccess, handleError('Error creating '+this.path));
            }

            this.Update = function(data) {
                return $http.put('/'+path+'/' + data.id, data).then(handleSuccess, handleError('Error updating '+this.path));
            }

            this.Delete = function(id) {
                return $http.delete('/'+this.path+'/' + id).then(handleSuccess, handleError('Error deleting '+this.path));
            }

            // private functions

            function handleSuccess(res) {
                return res.data;
            }

            function handleError(error) {
                return function () {
                    return { success: false, message: error };
                };
            }
        }


    }

})();
