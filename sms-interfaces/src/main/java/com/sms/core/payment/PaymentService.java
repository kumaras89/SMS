package com.sms.core.payment;

import java.util.List;

/**
 * Created by Ganesan on 25/06/16.
 */
public interface PaymentService {

    /**
     * @param paymentRequest
     * @return
     */
    PaymentDetail makePayment(PaymentInfo paymentRequest);

    /**
     * @param studentCode
     * @return
     */
    PaymentDetail getPaymentDetail(String studentCode);

    /**
     * @param paymentSearchCriteria
     * @return
     */
    List<PaymentSearchInfo> search(PaymentSearchCriteria paymentSearchCriteria);

}
