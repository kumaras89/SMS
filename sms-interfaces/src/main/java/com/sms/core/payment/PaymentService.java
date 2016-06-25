package com.sms.core.payment;

/**
 * Created by Ganesan on 25/06/16.
 */
public interface PaymentService {


    PaymentDetail makePayment(PaymentInfo paymentRequest);

    PaymentDetail getPaymentDetail(String studentCode);


}
