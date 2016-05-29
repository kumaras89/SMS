(function () {
    'use strict';

    angular
        .module('app')
        .factory('StorageService', StorageService);

    StorageService.$inject = ['$http'];
    function StorageService($http) {
        var service = {};

        service.getFromStoarage = function(url, callback) {
            var storageVal = localStorage.getItem(url);
            if(storageVal == null) {
                $http.get(url).then(function(response) {
                    var data = response.data;
                    if(!data.message) {
                        localStorage.setItem(url, JSON.stringify(data));
                        callback(data)
                    }
                });
            } else {
                callback(JSON.parse(storageVal));
            }
        }

        service.getTrustedStoarage = function(url) {
            var storageVal = localStorage.getItem(url);
            return JSON.parse(storageVal)
        }

        service.clearStorage = function(url) {
            localStorage.removeItem(url);
        }

        return service;

    }

})();