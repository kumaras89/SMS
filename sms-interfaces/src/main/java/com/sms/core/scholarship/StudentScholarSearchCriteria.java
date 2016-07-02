package com.sms.core.scholarship;

/**
 * Created by Ganesan on 02/07/16.
 */
public class StudentScholarSearchCriteria {

    private String studentName;
    private String branchName;
    private ScholarStatus status;
    private String marketingEmployeeName;
    private Integer year;

    public String getStudentName() {
        return studentName;
    }

    public String getBranchName() {
        return branchName;
    }

    public Integer getYear() {
        return year;
    }

    public ScholarStatus getStatus() {
        return status;
    }

    public String getMarketingEmployeeName() {
        return marketingEmployeeName;
    }
}
