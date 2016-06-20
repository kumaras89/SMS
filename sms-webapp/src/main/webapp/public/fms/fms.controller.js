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
                           var doctypes = res.data;
                            _.each(doctypes, function(dt) {
                                $scope.docs[dt.id+'-'+0] = {};
                                $scope.docs[dt.id+'-'+0].docType = dt;
                                $scope.docs[dt.id+'-'+0].fileSequence = 0;
                            });
                            $http.get('/document/documents/'+ $scope.uploaderid).then(
                                function(res) {
                                    var documents = res.data;
                                    _.each(documents, function(doc) {
                                        var key = doc.documentTypeId+'-'+doc.fileSequence;
                                        var foundDoc = $scope.docs[key];
                                        if(typeof foundDoc === undefined) {
                                            $scope.docs[key] = {}
                                            $scope.docs[key].docType = $scope.docs[doc.documentTypeId+'-'+0].docType;
                                        }
                                        $scope.docs[key].doc = doc;
                                        $scope.docs[key].fileSequence = doc.fileSequence;
                                    });
                                }
                            );
                        }
                    );

                }

                $scope.isPresent = function(key) {
                    return $scope.docs[key].doc;
                }

                $scope.uploadCommpleted = function () {

                }

                $scope.upload = function (key) {
                    var foundDoc = $scope.docs[key];
                    var data = {
                        uploadInfo: {
                            documentTypeId : foundDoc.docType.id,
                            uploaderId : $scope.uploaderid,
                            fileInfo : foundDoc.fileInfo,
                            fileName : foundDoc.file.name,
                            fileSequence : foundDoc.fileSequence
                        },
                        file: foundDoc.file
                    };
                    var fd = new FormData();
                    fd.append("uploadinfo", angular.toJson(data.uploadInfo,true));
                    fd.append("file", data.file);

                    $http.post("/document/upload", fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    }).
                    success(function (data, status, headers, config) {
                        $scope.docs[key].doc = data;
                    }).
                    error(function (data, status, headers, config) {
                        alert("failed!");
                    });

                }

                $scope.download = function (key) {
                    console.log('download' + id + 'from  '+$scope.docs);
                    var docId = $scope.docs[key].doc.id;
                    var fileName = $scope.docs[key].doc.fileName
                    $window.open('/document/download/'+docId+'/'+fileName);
                }

                $scope.delete = function (key) {
                    $http.delete('/document/delete/'+$scope.docs[key].doc.id)
                        .success(function(data) {
                            $scope.docs[key].doc = undefined;
                        }).
                    error(function () {
                        alert("failed!");
                    });
                }

                $scope.keys = function(obj){
                    return obj? Object.keys(obj) : [];
                }

                $scope.init();

            }]);

})();
