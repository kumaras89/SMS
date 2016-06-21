package com.sms.core.marketing;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.common.Designation;
import com.sms.core.student.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SMS_TR_MARKETING_EMPLOYEE")
public class MarketingEmployee extends BaseModel {

    @NotNull(message = "Marketing Employee Code is empty")
    @Size(min = 1, message = "Marketing Employee Code is empty")
    @Column(name = "ME_CODE")
    private String code;


    @NotNull(message = "Marketing Employee name is empty")
    @Size(min = 1, message = "Marketing Employee name is empty")
    @Column(name = "ME_NAME")
    private String name;


    @Column(name = "ME_DESIGNATION")
    private Designation designation;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ME_ADDRESS_ID")
    private Address address;

    @NotNull(message = "Phone nnumber is empty")
    @Digits(integer = 10,fraction = 0, message = "Phone number is invalid")
    @Column(name = "ME_PHONE_NUMBER")
    private String phoneNumber;

    public static Builder<MarketingEmployee> builder() {
        return Builder.of(MarketingEmployee.class);
    }

    public static Builder<MarketingEmployee> toBuilder(final MarketingEmployeeInfo marketingEmployeeInfo) {
        return builder()
                .with(MarketingEmployee::getId, marketingEmployeeInfo.getId())
                .with(MarketingEmployee::getCode, marketingEmployeeInfo.getCode())
                .with(MarketingEmployee::getDesignation, Designation.valueOf(marketingEmployeeInfo.getDesignation()))
                .with(MarketingEmployee::getName, marketingEmployeeInfo.getName())
                .with(MarketingEmployee::getAddress, marketingEmployeeInfo.getAddress())
                .with(MarketingEmployee::getPhoneNumber, marketingEmployeeInfo.getPhoneNumber());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
