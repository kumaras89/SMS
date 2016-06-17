(function () {
    'use strict';

    angular
        .module('FMS')
        .controller('FMSCtrl', ['$scope', '$http', '$stateParams', '$window',
            function ($scope,$http, $stateParams, $window) {

                $scope.init = function() {
                    $scope.cateory = $stateParams.category;
                    $scope.uploaderid = $stateParams.uploaderid;
                    $scope.docs = {};
                    $http.get('/document/doctypes/'+ $scope.cateory).then(
                        function(res) {
                           $scope.doctypes = res.data;
                            $http.get('/document/documents/'+ $scope.uploaderid).then(
                                function(res) {
                                    $scope.documents = res.data;
                                    _.each($scope.doctypes, function(dt) {
                                        $scope.docs[dt.id] = {};
                                        $scope.docs[dt.id].doc =
                                            _.find($scope.documents , function(doc) {
                                                return doc.documentTypeId == dt.id
                                            })
                                    })
                                }
                            );
                        }
                    );

                }

                $scope.isPresent = function(id) {
                    return $scope.docs[id].doc;
                }

                $scope.uploadCommpleted = function () {

                }

                $scope.upload = function (id) {
                    var data = {
                        uploadInfo: {
                            documentTypeId : id,
                            uploaderId : $scope.uploaderid,
                            fileInfo : $scope.docs[id].fileInfo,
                            fileName : $scope.docs[id].file.name
                        },
                        file: $scope.docs[id].file
                    };
                    var fd = new FormData();
                    fd.append("uploadinfo", angular.toJson(data.uploadInfo,true));
                    fd.append("file", data.file);

                    $http.post("/document/upload", fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    }).
                    success(function (data, status, headers, config) {
                        $scope.docs[id].doc = data;
                    }).
                    error(function (data, status, headers, config) {
                        alert("failed!");
                    });

                }

                $scope.download = function (id) {
                    console.log('download' + id + 'from  '+$scope.docs);
                    var docId = $scope.docs[id].doc.id;
                    var fileName = $scope.docs[id].doc.fileName
                    $window.open('/document/download/'+docId+'/'+fileName);
                }

                $scope.delete = function (id) {
                    $http.delete('/document/delete/'+docId)
                        .success(function(data) {
                            $scope.docs[dt.id].doc = undefined;
                        }).
                    error(function (data, status, headers, config) {
                        alert("failed!");
                    });
                }

                $scope.init();

            }]);

})();
