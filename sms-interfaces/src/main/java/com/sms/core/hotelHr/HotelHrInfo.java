package com.sms.core.hotelHr;

import com.sms.core.common.Builder;
import com.sms.core.hotel.Hotel;
import com.sms.core.hotel.HotelInfo;
import com.sms.core.student.Address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by sathish on 7/18/2016.
 */

public class HotelHrInfo
{

    private long id;

    @NotNull(message = "Hr Name is Empty")
    private String name;

    @NotNull(message = "Phonenumber is Empty")
    private String phoneNumber;

    @NotNull(message = "Hotel Name is Empty")
    private String hotelCode;

    @Valid
    @NotNull(message = "Address detais is Empty")
    private Address address;

    public HotelHrInfo() {
    }

    public static Builder<HotelHrInfo> builder() {
        return Builder.of(HotelHrInfo.class);
    }

    public static Builder<HotelHrInfo> toBuilder(final HotelHr hotelHr) {
        return builder()
                .with(HotelHrInfo::getId, hotelHr.getId())
                .on(HotelHrInfo::getAddress).set(hotelHr.getAddress())
                .on(HotelHrInfo::getHotelCode).set(hotelHr.getHotel().getHotelCode())
                .on(HotelHrInfo::getName).set(hotelHr.getName())
                .on(HotelHrInfo::getPhoneNumber).set(hotelHr.getPhoneNumber());
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

    public String getHotelCode() {
        return hotelCode;
    }

    public Address getAddress() {
        return address;
    }
}
