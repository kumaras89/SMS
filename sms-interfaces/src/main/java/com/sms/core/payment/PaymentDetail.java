package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.feesparticular.FeesDetail;

import java.util.List;

/**
 * Created by Ganesan on 25/06/16.
 */
public class PaymentDetail {

    private FeesDetail actualPaymentDetail;
    private FeesDetail paidDetail;
    private FeesDetail remainingPaymentDetail;
    private List<PaymentInfo> paymentHistory;

    public FeesDetail getActualPaymentDetail() {
        return actualPaymentDetail;
    }

    public FeesDetail getPaidDetail() {
        return paidDetail;
    }

    public FeesDetail getRemainingPaymentDetail() {
        return remainingPaymentDetail;
    }

    public List<PaymentInfo> getPaymentHistory() {
        return paymentHistory;
    }

    public static Builder<PaymentDetail> builder() {
        return Builder.of(PaymentDetail.class);
    }

    public static Builder<PaymentDetail> builder(PaymentDetail pd) {
        return Builder.of(PaymentDetail.class, pd);
    }
}
