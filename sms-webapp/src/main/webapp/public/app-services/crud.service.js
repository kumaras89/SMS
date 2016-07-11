(function () {
    'use strict';

    angular
        .module('app')
        .factory('CrudService', CrudService);

    CrudService.$inject = ['$http','StorageService'];
    function CrudService($http, StorageService) {

        
        
        var service = {};

        service.of = function(path) {
            return new Service(path)
        }

        service.branchService = service.of('branch')
        service.userService = service.of('user')
        service.courseService = service.of('course')
        service.schemeService = service.of('scheme')
        service.feesParticularService = service.of('feesParticular')
        service.scholarshipEnrollmentService= service.of('scholarshipenrollment')
        service.studentService = service.of('student')
        service.marketingEmployeeService = service.of('marketingEmployee')
        service.idcardService = service.of('idcard')

        //added for confirmation deletion

        service.roleService = service.of('role');
        service.securedOperationService = service.of('securedoperation');

        service.scholarshipEnrollmentService = service.of('scholarshipenrollment')
        return service;

        function Service(path) {
            this.path = path;
            this.GetAll =  function() {
                var res = $http.get('/'+this.path).then(handleSuccess)
                return res;
            }

            this.GetById = function(id) {
                return $http.get( '/'+this.path +'/'+ id).then(handleSuccess);
            }

            this.Create = function(data) {
                var res = $http.post('/'+this.path, data).then(function(res){
                    StorageService.clearStorage('/'+path)
                    return handleSuccess(res)
                });
                return res;
            }

            this.Update = function(data) {
                var res =  $http.put('/'+path+'/' + data.id, data).then(function(res){
                    StorageService.clearStorage('/'+path)
                    return handleSuccess(res)
                });
                return res;
            }

            this.Delete = function(id) {
                return $http.delete('/'+this.path+'/' + id).then(function(res){
                    StorageService.clearStorage('/'+path)
                    return handleSuccess(res)
                });
            }

            this.Search =  function(data) {
                return $http.post('/'+this.path,data).then(handleSuccess)
            }

            // private functions

            function handleSuccess(res) {

                return res.data;
            }

        }


    }

})();
