package com.sms.core.hotel;

import com.sms.core.branch.Branch;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sathish on 7/14/2016.
 */
@Entity
@Table(name = "SMS_TR_HOTEL")
@SequenceGenerator(name = "SMS_SQ_HL",sequenceName = "SMS_SQ_HL")
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_HL")
    private long id;

    @Column(name = "HL_CODE")
    private String hotelCode;
    @Column(name = "HL_NAME")
    private String hotelName;
    @Column(name = "Hl_PHONE_NUMBER")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HL_ADDRESS_ID")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "HL_BRANCH_ID")
    private Branch branch;

    @Enumerated(EnumType.STRING)
    @Column(name = "HL_STATUS")
    private HotelStatus status;

    @Column(name = "HL_CREATEDDATE")
    private Date createdDate;


    public static Builder<Hotel> builder() { return Builder.of(Hotel.class); }

    public static Builder<Hotel> toBuilder(final HotelInfo hotelInfo) {
        return builder()
                .on(Hotel::getStatus).set(HotelStatus.valueOf(hotelInfo.getStatus()))
                .on(Hotel::getCreatedDate).set(new Date())
                .on(Hotel::getHotelName).set(hotelInfo.getHotelName())
                .on(Hotel::getPhoneNumber).set(hotelInfo.getPhoneNumber())
                .on(Hotel::getId).set(hotelInfo.getId())
                .on(Hotel::getHotelCode).set(hotelInfo.getHotelCode())
                .on(Hotel::getAddress).set(hotelInfo.getAddress());

    }


    public long getId() {
        return id;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Branch getBranch() {
        return branch;
    }

    public HotelStatus getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
