(function () {
    'use strict';

    angular
        .module('Student')
        .filter('mySum', function () {
            return function (items) {
                var sum = 0;
                angular.forEach(items, function (item, index) {
                    if (item.amount) {
                        sum += item.amount;
                    }
                })
                return sum;
            }
        })
        .controller('PaymentCtrl', ['$scope', '$stateParams', 'FlashService', '$state', '$http',
            function ($scope, $stateParams, FlashService, $state, $http) {

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
                    $state.go('home.payment-creation' , {studentid: $scope.studentId });
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


                $scope.loadPaymentDetail();

            }]);

})();
