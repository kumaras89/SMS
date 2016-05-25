(function () {
    'use strict';

    angular
        .module('app')
        .factory('StorageService', StorageService);

    StorageService.$inject = ['$http'];
    function StorageService($http) {
        var service = {};

        service.getFromStoarage = function(key, url, callback) {
            var storageVal = localStorage.getItem(key);
            if(storageVal == null) {
                $http.get(url).then(function(response) {
                    var data = response.data;
                    if(!data.message) {
                        localStorage.setItem(key, JSON.stringify(data));
                        callback(data)
                    }
                });
            } else {
                callback(JSON.parse(storageVal));
            }
        }

        service.getTrustedStoarage = function(key) {
            var storageVal = localStorage.getItem(key);
            return JSON.parse(storageVal)
        }

        return service;

    }

})();