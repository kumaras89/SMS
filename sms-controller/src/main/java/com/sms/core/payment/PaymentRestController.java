package com.sms.core.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ganesan on 25/06/16.
 */
@RestController
@RequestMapping("/payment")
public class PaymentRestController {

    private PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDetail makePayment(@RequestBody @Valid PaymentInfo paymentInfo) {
        return paymentService.makePayment(paymentInfo);

    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDetail paymentDetail(@PathVariable("studentId")  String studentId) {
        return paymentService.getPaymentDetail(studentId);

    }
}
