(function () {
    'use strict';

    angular
        .module('Student')
        .controller('PaymentCtrl', ['$scope', '$stateParams', 'FlashService', '$state', '$http', '$timeout',
            function ($scope, $stateParams, FlashService, $state, $http, $timeout) {

                $scope.shownHistory = [];
                $scope.paymentChanged = function () {
                    $scope.paymentMode=$scope.paymentMode;
                }

                $scope.loadPaymentDetail = function () {
                    $scope.studentId = $stateParams.studentid
                    $http.get('/payment/'+$scope.studentId).then(function(res){
                        $scope.paymentDetail = res.data
                        $scope.payment = {}
                        $scope.payment.studentCode = $scope.studentId
                        $scope.payment.amount = $scope.paymentDetail.remainingPaymentDetail.amount
                        $scope.payment.feesInfos = []
                        angular.copy($scope.paymentDetail.remainingPaymentDetail.detailedFees, $scope.payment.feesInfos)
                        $scope.paymentMode='cash';
                    })
                    $http.get('/student/code/'+$scope.studentId).then(function(res){
                        $scope.student = res.data;
                    })

                }

                $scope.goToPaymentList = function () {
                    $state.go('home.payment-list');
                }

                $scope.goToPaymentCreation = function () {
                    var amt = $scope.calcTotalAmount();
                    if(amt == 0) {
                        FlashService.Success("All Payment Due are already paid!");
                    } else {
                        $state.go('home.payment-creation' , {studentid: $scope.studentId });
                    }


                }

                $scope.goToPaymentDetail = function () {
                    $state.go('home.payment-detail' , {studentid: $scope.studentId });
                }

                $scope.makePayment = function() {
                    if($scope.paymentMode='cash'){
                        $scope.payment.ddNumber='Cash Transaction';
                        $scope.payment.bankName='Cash Transaction';
                        $scope.payment.bankBranchName='Cash Transaction';

                    }
                    $http.post('/payment', $scope.payment).then(function(){
                        $scope.goToPaymentDetail()
                        FlashService.Success("Payment Inserted Succesfully!!", true);
                    })
                }

                $scope.getFeesPurtDetailAmt = function(feesDetail, feesInfo) {
                    try {
                        var amt = _.find(feesDetail.detailedFees, function(df){
                            return df.feesParticularCode === feesInfo.feesParticularCode;
                        }).amount
                        amt = amt == null || amt== undefined? 0 : amt
                    } catch(e){
                        amt = 0;
                    }
                    return amt;
                }

                $scope.calcTotalAmount = function() {
                    var sum = 0
                    angular.forEach($scope.payment.feesInfos, function (item, index) {
                        if (item.amount) {
                            sum += item.amount
                        }
                    })
                    $scope.payment.amount = sum
                    return sum
                }

                $scope.isDisabled = function(feesInfo) {
                    return $scope.getFeesPurtDetailAmt($scope.paymentDetail.remainingPaymentDetail, feesInfo) == 0
                }

                $scope.toggleHistoryDetail = function(index) {
                    var i = $scope.shownHistory.indexOf(index);
                    if (i > -1) {
                        $scope.shownHistory.splice(i, 1);
                    } else {
                        $scope.shownHistory.push(index);
                    }
                }

                $scope.isShown = function(index) {
                    return $scope.shownHistory.indexOf(index) > -1
                }


                $scope.loadPaymentDetail();

            }])
        .controller('PaymentListCtrl', ['$scope','$location','ngTableParams','$http','$timeout','$stateParams','AdminService',
            function ($scope, $location,ngTableParams,$http,$timeout,$stateParams,AdminService) {
                $scope.searchCriteria={};
                $scope.searchCriteria.durationFrom='';
                $scope.searchCriteria.durationTo='';
                $scope.init=function () {
                    if($stateParams.branch){
                        $scope.searchCriteria.branchName=$stateParams.branch;
                    }
                    $scope.searchCriteria.durationFrom=new Date(moment());
                    $scope.searchCriteria.durationTo=new Date(moment());

                    AdminService.getBatches(function (data) {
                        $scope.batchNames = _.pluck(data, "name")
                    });

                }
                $scope.init();
                
                $scope.goToPayment = function(code) {
                    $location.path('/home/payment-detail/'+code);
                }

                $scope.search = function () {
                    if( $scope.searchCriteria.durationTo)
                    {
                        $scope.searchCriteria.durationTo=new Date($scope.searchCriteria.durationTo).setHours(23,59,59,59);
                    }
                    if( $scope.searchCriteria.durationFrom)
                    {
                        $scope.searchCriteria.durationFrom=new Date($scope.searchCriteria.durationFrom).setHours(0,0,0,0);
                    }
                    if(!$scope.searchCriteria){
                        $scope.searchCriteria={};
                    }
                    if ($scope.tableParams) {
                        $scope.tableParams.reload()
                    } else {
                        $scope.tableParams = new ngTableParams({
                            page: 1,            // show first pagez
                            count: 10,          // count per page
                            sorting: {
                                name: 'asc'     // initial sorting
                            }
                        }, {
                            total: 0,           // length of data
                            getData: function($defer, params) {
                                $http.post('/payment/search', $scope.searchCriteria).then(function(res) {
                                    var data = res.data;
                                    $timeout(function() {
                                        params.total(data.length);
                                        $defer.resolve(data);
                                    }, 10);
                                }, function() {
                                    $scope.entities = []
                                    $timeout(function() {
                                        params.total($scope.entities.length);
                                        $defer.resolve($scope.entities);
                                    }, 10);
                                })
                            }
                        });
                    }
                }

            }]);

})();