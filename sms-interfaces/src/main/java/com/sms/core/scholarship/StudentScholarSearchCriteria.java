package com.sms.core.scholarship;

import java.util.Date;

/**
 * Created by Ganesan on 02/07/16.
 */
public class StudentScholarSearchCriteria {

    private String studentName;
    private String branchName;
    private ScholarStatus status;
    private String marketingEmployeeName;
    /*private Integer year;*/  // After adding duration wise in search, year is removed
    private Date durationFrom;
    private Date durationTo;

    public Date getDurationFrom() { return durationFrom;}

    public Date getDurationTo() {return durationTo; }

    public String getStudentName() {
        return studentName;
    }

    public String getBranchName() {
        return branchName;
    }

   /* public Integer getYear() {
        return year;
    }*/

    public ScholarStatus getStatus() {
        return status;
    }

    public String getMarketingEmployeeName() {
        return marketingEmployeeName;
    }
}
