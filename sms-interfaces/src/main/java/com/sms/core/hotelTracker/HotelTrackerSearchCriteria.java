package com.sms.core.hotelTracker;

import java.util.Date;

/**
 * Created by sathish on 7/19/2016.
 */
public class HotelTrackerSearchCriteria
{
    private String hotelName;
    private String branchName;
    private HotelTrackerStatus status;
    private String hotelHrName;
    private String studentName;
    private String batchName;
    private Date durationFrom;
    private Date durationTo;


    public String getHotelName() {
        return hotelName;
    }

    public String getBranchName() {
        return branchName;
    }

    public HotelTrackerStatus getStatus() {
        return status;
    }

    public String getHotelHrName() {
        return hotelHrName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getBatchName() {  return batchName;  }

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getDurationTo() {
        return durationTo;
    }
}
