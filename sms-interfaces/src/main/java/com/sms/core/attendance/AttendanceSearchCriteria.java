package com.sms.core.attendance;

import java.util.Date;

/**
 * Created by sathish on 8/15/2016.
 */
public class AttendanceSearchCriteria {
    private Date durationFrom;
    private Date durationTo;
    private String branchName;
    private String courseName;
    private String userName;
    private Integer batch;
    private String studentName;

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getDurationTo() {
        return durationTo;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getBatch() {
        return batch;
    }

    public String getStudentName() {
        return studentName;
    }
}
