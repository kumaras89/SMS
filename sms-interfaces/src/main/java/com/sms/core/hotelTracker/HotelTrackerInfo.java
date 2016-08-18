package com.sms.core.hotelTracker;

import com.sms.core.common.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sathish on 7/19/2016.
 */
public class HotelTrackerInfo
{
    private long id;

    @NotNull(message = "HotelTrackerCode is Empty")
    private String hotelTrackerCode;

    @NotNull(message = "Branch Name is Empty")
    private String branchCode;

    @NotNull(message = "Hotel Name is Empty")
    private String hotelCode;

    @NotNull(message = "Hotel Hr Name is Empty")
    private long hotelHrId;

    @NotNull(message = "Student Name is Empty")
    private String studentCode;

    @NotNull(message = "DurationTo is Empty")
    private Date durationTo;

    @NotNull(message = "DurationFrom is Empty")
    private Date durationFrom;

    private Date createdDate;

    private Date modifiedDate;

    @NotNull(message = "Status is Empty")
    private String status;

    @NotNull(message = "Remarks is Empty")
    private String remarks;

    @NotNull(message = "Stipend is Empty")
    private BigDecimal stipend;

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
                .on(HotelTrackerInfo::getRemarks).set(hotelTracker.getRemarks())
                .on(HotelTrackerInfo::getHotelTrackerCode).set(hotelTracker.getHotelTrackerCode())
                .on(HotelTrackerInfo::getStipend).set(hotelTracker.getStipend());
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

    public String getHotelTrackerCode() {
        return hotelTrackerCode;
    }

    public BigDecimal getStipend() { return stipend; }
}
