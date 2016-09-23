package com.sms.core.payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ganesan on 25/06/16.
 * <p></p>
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
    List<PaymentSearchInfo> search(final PaymentSearchCriteria paymentSearchCriteria);

    /**
     * @param paymentSearchCriteria
     * @return
     */
    BigDecimal totalIncome(final PaymentSearchCriteria paymentSearchCriteria);
}
