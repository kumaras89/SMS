package com.sms.core.hotel;

import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * Created by sathish on 7/14/2016.
 */
public class HotelInfo {
    private long id;

    @NotNull(message = "Hotelcode is Empty")
    private String hotelCode;

    @NotNull(message = "Hotelname is Empty")
    private String hotelName;

    @NotNull(message = "Phone number is empty")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digists")
    private String phoneNumber;

    @Valid
    @NotNull(message = "Address detais is Empty")
    private Address address;

    @NotNull(message = "Branch Name is Empty")
    private String branchCode;

    private String status;

    private Date createdDate;

    public HotelInfo() {

    }

    public static Builder<HotelInfo> builder() {
        return Builder.of(HotelInfo.class);
    }

    public static Builder<HotelInfo> toBuilder(final Hotel hotel) {
        return builder()
            .on(HotelInfo::getId).set(hotel.getId())
            .on(HotelInfo::getAddress).set(hotel.getAddress())
            .on(HotelInfo::getBranchCode).set(hotel.getBranch().getCode())
            .on(HotelInfo::getHotelCode).set(hotel.getHotelCode())
            .on(HotelInfo::getStatus).set(hotel.getStatus().name())
            .on(HotelInfo::getHotelName).set(hotel.getHotelName())
            .on(HotelInfo::getPhoneNumber).set(hotel.getPhoneNumber())
            .on(HotelInfo::getCreatedDate).set(hotel.getCreatedDate());
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

    public String getBranchCode() {
        return branchCode;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
