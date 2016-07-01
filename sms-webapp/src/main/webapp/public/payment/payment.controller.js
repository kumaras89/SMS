(function () {
    'use strict';

    angular
        .module('Student')
        .controller('PaymentCtrl', ['$scope', '$stateParams', 'FlashService', '$state', '$http', '$timeout',
            function ($scope, $stateParams, FlashService, $state, $http, $timeout) {

                $scope.shownHistory = []

                $scope.loadPaymentDetail = function () {
                    $scope.studentId = $stateParams.studentid
                    $http.get('/payment/'+$scope.studentId).then(function(res){
                        $scope.paymentDetail = res.data
                        $scope.payment = {}
                        $scope.payment.studentCode = $scope.studentId
                        $scope.payment.amount = $scope.paymentDetail.remainingPaymentDetail.amount
                        $scope.payment.feesInfos = $scope.paymentDetail.remainingPaymentDetail.detailedFees
                    })
                    $http.get('/student/code/'+$scope.studentId).then(function(res){
                        $scope.student = res.data;
                    })

                }

                $scope.viewStudent = function () {
                    $state.go('home.student-detail' , {id: $scope.student.id});
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

            }]);

})();
