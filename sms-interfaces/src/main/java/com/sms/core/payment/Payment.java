package com.sms.core.payment;

import com.sms.core.common.Builder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Ram on 18-06-2016.
 */
public class Payment {
    private BigDecimal amount;
    private String ddNumber;
    private Date paidDate;
    private String bankName;
    private String bankBranchName;
    private String admittedOrRejectedBy;
    private String approvedBy;
    private String admittedBy;
    private Set<PaymentFees> paymentFees;


    public Set<PaymentFees> getPaymentFees() {
        return paymentFees;
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

    public static Builder<Payment> builder() {
        return Builder.of(Payment.class);
    }


}
