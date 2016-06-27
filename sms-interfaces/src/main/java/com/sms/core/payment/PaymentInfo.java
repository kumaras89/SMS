package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.feesparticular.FeesInfo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Ganesan on 25/06/16.
 */
public class PaymentInfo {

    private String studentCode;
    private List<FeesInfo> feesInfos;
    private BigDecimal amount;
    private String ddNumber;
    private Date paidDate;
    private String bankName;
    private String bankBranchName;
    private String admittedOrRejectedBy;
    private String approvedBy;
    private String admittedBy;


    public String getStudentCode() {
        return studentCode;
    }

    public List<FeesInfo> getFeesInfos() {
        return feesInfos;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDdNumber() {
        return ddNumber;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public String getAdmittedOrRejectedBy() {
        return admittedOrRejectedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public String getAdmittedBy() {
        return admittedBy;
    }


    public static PaymentInfo build(Payment payment) {
        return Builder.of(PaymentInfo.class)
                .on(PaymentInfo::getStudentCode).set(payment.getStudent().getCode())
                .on(PaymentInfo::getAmount).set(payment.getAmount())
                .on(PaymentInfo::getFeesInfos).set(FList.of(payment.getPaymentFees()).map(FeesInfo::build).get())
                .on(PaymentInfo::getDdNumber).set(payment.getDdNumber())
                .on(PaymentInfo::getBankName).set(payment.getBankName())
                .on(PaymentInfo::getPaidDate).set(payment.getPaidDate())
                .on(PaymentInfo::getBankBranchName).set(payment.getBankBranchName())
                .on(PaymentInfo::getAdmittedOrRejectedBy).set(payment.getAdmittedOrRejectedBy())
                .on(PaymentInfo::getApprovedBy).set(payment.getApprovedBy())
                .on(PaymentInfo::getAdmittedBy).set(payment.getAdmittedBy())
                .build();
    }


}
