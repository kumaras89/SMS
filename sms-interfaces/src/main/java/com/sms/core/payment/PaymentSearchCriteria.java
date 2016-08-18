package com.sms.core.payment;

import java.util.Date;

/**
 * Created by Assaycr-04 on 8/11/2016.
 */
public class PaymentSearchCriteria {


    private String studentName;
    private String studentCode;
    private String branchName;
    private Date durationFrom;
    private Date durationTo;

    public String getStudentName() {return studentName;}

    public String getStudentCode() { return studentCode;}

    public String getBranchName() { return branchName;  }

    public Date getDurationFrom() { return durationFrom; }

    public Date getDurationTo() { return durationTo; }
}
