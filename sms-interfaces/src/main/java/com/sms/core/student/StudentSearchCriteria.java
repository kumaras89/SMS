package com.sms.core.student;

import java.util.Date;

/**
 * Created by Ganesan on 02/07/16.
 */
public class StudentSearchCriteria {

    private String studentName;
    private String studentCode;
    private String branchName;
    private Integer year;
    private Date durationFrom;
    private Date durationTo;

    public Date getDurationFrom() { return durationFrom; }

    public Date getDurationTo() { return durationTo; }



    public String getStudentName() {
        return studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public Integer getYear() {
        return year;
    }
}
