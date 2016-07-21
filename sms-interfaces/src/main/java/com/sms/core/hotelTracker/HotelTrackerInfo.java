package com.sms.core.hotelTracker;

import com.sms.core.common.Builder;

import java.util.Date;

/**
 * Created by sathish on 7/19/2016.
 */
public class HotelTrackerInfo
{
    private long id;

    private String branchCode;

    private String hotelCode;

    private long hotelHrId;

    private String studentCode;

    private Date durationTo;

    private Date durationFrom;

    private Date createdDate;

    private Date modifiedDate;

    private String status;

    private String remarks;

    public HotelTrackerInfo()
    {
    }

    public static Builder<HotelTrackerInfo> builder() {
        return Builder.of(HotelTrackerInfo.class);
    }

    public static Builder<HotelTrackerInfo> toBuilder(final HotelTracker hotelTracker) {
        return builder()
                .on(HotelTrackerInfo::getId).set(hotelTracker.getId())
                .on(HotelTrackerInfo::getHotelCode).set(hotelTracker.getHotelName().getHotelCode())
                .on(HotelTrackerInfo::getBranchCode).set(hotelTracker.getBranchName().getCode())
                .on(HotelTrackerInfo::getHotelHrId).set(hotelTracker.getHotelHrName().getId())
                .on(HotelTrackerInfo::getStudentCode).set(hotelTracker.getStudentName().getCode())
                .on(HotelTrackerInfo::getDurationFrom).set(hotelTracker.getDurationFrom())
                .on(HotelTrackerInfo::getDurationTo).set(hotelTracker.getDurationTo())
                .on(HotelTrackerInfo::getCreatedDate).set(hotelTracker.getCreatedDate())
                .on(HotelTrackerInfo::getModifiedDate).set(hotelTracker.getModifiedDate())
                .on(HotelTrackerInfo::getStatus).set(hotelTracker.getStatus().name())
                .on(HotelTrackerInfo::getRemarks).set(hotelTracker.getRemarks());
    }


    public long getId() {
        return id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public long getHotelHrId() {
        return hotelHrId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public Date getDurationTo() {
        return durationTo;
    }

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }
}
