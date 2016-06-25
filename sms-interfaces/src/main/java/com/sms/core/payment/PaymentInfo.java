package com.sms.core.payment;

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


}
