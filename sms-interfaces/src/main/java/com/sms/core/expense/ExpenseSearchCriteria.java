package com.sms.core.expense;

import java.util.Date;

/**
 * Created by sathish on 8/16/2016.
 */
public class ExpenseSearchCriteria
{

    private String userName;
    private String branchName;
    private Date durationTo;
    private Date durationFrom;

    public String getUserName() {
        return userName;
    }

    public String getBranchName() {
        return branchName;
    }

    public Date getDurationTo() {
        return durationTo;
    }

    public Date getDurationFrom() {
        return durationFrom;
    }
}
