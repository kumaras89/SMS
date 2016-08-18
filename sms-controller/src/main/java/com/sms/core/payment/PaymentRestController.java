package com.sms.core.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ResponseEntity<List<PaymentSearchInfo>> search(@RequestBody @Valid PaymentSearchCriteria criteria) {
        return Optional.ofNullable(paymentService.search(criteria))
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
