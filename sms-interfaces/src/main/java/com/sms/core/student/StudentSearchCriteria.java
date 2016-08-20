package com.sms.core.student;

import java.util.Date;

/**
 * Created by Ganesan on 02/07/16.
 */
public class StudentSearchCriteria {

    private String studentName;
    private String studentCode;
    private String branchName;
    private String courseName;
    private Integer batch;
    private Integer year;
    private Date durationFrom;
    private Date durationTo;
    private String villageName;

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getDurationTo() {
        return durationTo;
    }

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

    public String getCourseName() {
        return courseName;
    }

    public Integer getBatch() {
        return batch;
    }

    public String getVillageName() {
        return villageName;
    }
}
