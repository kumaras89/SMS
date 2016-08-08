package com.sms.core.hotelTracker;

import com.sms.core.branch.Branch;
import com.sms.core.common.Builder;
import com.sms.core.hotel.Hotel;
import com.sms.core.hotelHr.HotelHr;
import com.sms.core.student.Student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sathish on 7/19/2016.
 */
@Entity
@Table(name = "SMS_TR_HOTEL_TRACKER")
@SequenceGenerator(name = "SMS_SQ_HT",sequenceName = "SMS_SQ_HT" ,allocationSize = 1)
public class HotelTracker
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_HT")
    private long id;

    @Column(name = "HT_CODE")
    private String hotelTrackerCode;

    @ManyToOne
    @JoinColumn(name = "HT_BRANCH_ID")
    private Branch branchName;

    @ManyToOne
    @JoinColumn(name = "HT_HOTEL_ID")
    private Hotel hotelName;

    @ManyToOne
    @JoinColumn(name = "HT_HOTEL_HR_ID")
    private HotelHr hotelHrName;

    @ManyToOne
    @JoinColumn(name = "HT_STUDENT_ID")
    private Student studentName;

    @Column(name = "HT_DURATION_TO")
    private Date durationTo;

    @Column(name = "HT_DURATION_FROM")
    private Date durationFrom;

    @Column(name = "HT_CREATED_DATE")
    private Date createdDate;

    @Column(name = "HT_MODIFIED_DATE")
    private Date modifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "HT_STATUS")
    private HotelTrackerStatus status;

    @Column(name = "HT_REMARKS")
    private String remarks;

    public HotelTracker()
    {

    }

    public static Builder<HotelTracker> builder() { return Builder.of(HotelTracker.class); }

    public static Builder<HotelTracker> toBuilder(final HotelTrackerInfo hotelTrackerInfo) {
        return builder()
                .on(HotelTracker::getStatus).set(HotelTrackerStatus.valueOf(hotelTrackerInfo.getStatus()))
                .on(HotelTracker::getRemarks).set(hotelTrackerInfo.getRemarks())
                .on(HotelTracker::getModifiedDate).set(new Date())
                .on(HotelTracker::getCreatedDate).set(new Date())
                .on(HotelTracker::getId).set(hotelTrackerInfo.getId())
                .on(HotelTracker::getDurationFrom).set(hotelTrackerInfo.getDurationFrom())
                .on(HotelTracker::getDurationTo).set(hotelTrackerInfo.getDurationTo())
                .on(HotelTracker::getHotelTrackerCode).set(hotelTrackerInfo.getHotelTrackerCode());

    }

    public long getId() {
        return id;
    }

    public Branch getBranchName() {
        return branchName;
    }

    public Hotel getHotelName() {
        return hotelName;
    }

    public HotelHr getHotelHrName() {
        return hotelHrName;
    }

    public Student getStudentName() {
        return studentName;
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

    public HotelTrackerStatus getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getHotelTrackerCode() {
        return hotelTrackerCode;
    }
}
