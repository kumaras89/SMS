package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.common.FList;

/**
 * Created by Assaycr-04 on 8/16/2016.
 */
public class PaymentSearchInfo {


    private PaymentInfo paymentInfo;
    private String studentName;
    private String branchName;

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getBranchName() {
        return branchName;
    }

    public static PaymentSearchInfo build(final Payment payment) {
        return Builder.of(PaymentSearchInfo.class)
                .on(PaymentSearchInfo::getStudentName).set(payment.getStudent().getName())
                .on(PaymentSearchInfo::getBranchName).set(payment.getStudent().getBranch().getName())
                .on(PaymentSearchInfo::getPaymentInfo).set(PaymentInfo.build(payment))
                .build();
    }
}