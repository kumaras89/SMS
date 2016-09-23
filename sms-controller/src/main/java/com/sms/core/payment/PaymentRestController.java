package com.sms.core.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ganesan on 25/06/16.
 * <p></p>
 */
@RestController
@RequestMapping("/payment")
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDetail makePayment(@RequestBody @Valid final PaymentInfo paymentInfo) {
        return paymentService.makePayment(paymentInfo);

    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDetail paymentDetail(@PathVariable("studentId") final String studentId) {
        return paymentService.getPaymentDetail(studentId);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentSearchInfo>> search(@RequestBody @Valid final PaymentSearchCriteria criteria) {
        return Optional.ofNullable(paymentService.search(criteria))
            .filter(e -> !e.isEmpty())
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/totalIncome", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> totalIncome(@RequestBody @Valid final PaymentSearchCriteria criteria) {
        return new ResponseEntity<>(paymentService.totalIncome(criteria), HttpStatus.OK);
    }
}
