package com.sms.core.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ganesan on 25/06/16.
 */
@RestController("/payment")
public class PaymentRestController {

    private PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public ResponseEntity<PaymentDetail> makePayment(PaymentInfo paymentInfo) {
//        return paymentService.makePayment(paymentInfo);
        return null;
    }

    public ResponseEntity<PaymentDetail> paymentDetail(String studentId) {
//        return paymentService.makePayment(paymentInfo);
        return null;
    }
}
