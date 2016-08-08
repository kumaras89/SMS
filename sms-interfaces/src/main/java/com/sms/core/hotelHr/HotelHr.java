package com.sms.core.hotelHr;

import com.sms.core.common.Builder;
import com.sms.core.hotel.Hotel;
import com.sms.core.student.Address;

import javax.persistence.*;

/**
 * Created by sathish on 7/18/2016.
 */
@Entity
@Table(name = "SMS_TR_HOTEL_HR")
@SequenceGenerator(name = "SMS_SQ_HR",sequenceName = "SMS_SQ_HR",allocationSize = 1)
public class HotelHr
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_HR")
    private long id;

    @Column(name = "HR_NAME")
    private String name;

    @Column(name = "HR_CODE")
    private String hrCode;

    @Column(name = "HR_PHONE_NUMBER")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "HR_HOTEL_ID")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HR_ADDRESS_ID")
    private Address address;


    public static Builder<HotelHr> builder() {
        return Builder.of(HotelHr.class);
    }

    public static Builder<HotelHr> toBuilder(final HotelHrInfo hotelHrInfo) {
        return builder()
                .with(HotelHr::getId, hotelHrInfo.getId())
                .on(HotelHr::getAddress).set(hotelHrInfo.getAddress())
                .on(HotelHr::getName).set(hotelHrInfo.getName())
                .on(HotelHr::getPhoneNumber).set(hotelHrInfo.getPhoneNumber())
                .on(HotelHr::getHrCode).set(hotelHrInfo.getHrCode());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Address getAddress() {
        return address;
    }

    public String getHrCode() {
        return hrCode;
    }
}
