package com.sms.core.marketing;

import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MarketingEmployeeInfo {

    private Long id;

    @NotNull(message = "Marketing Employee Code is empty")
    @Size(min = 1, message = "Marketing Employee Code is empty")
    private String code;

    @NotNull(message = "Marketing Employee name is empty")
    @Size(min = 1, message = "Marketing Employee name is empty")
    private String name;

    @NotNull(message = "Designation is empty")
    @Size(min = 1, message = "Designation is empty")
    private String designation;

    @Valid
    @NotNull
    private Address address;

    @NotNull(message = "Phone nnumber is empty")
    @Size(min = 10,max=13,message = "Phone number is invalid")
    private String phoneNumber;

    public static Builder<MarketingEmployeeInfo> builder() {
        return Builder.of(MarketingEmployeeInfo.class);
    }

    public static Builder<MarketingEmployeeInfo> toBuilder(final MarketingEmployee marketingEmployee) {
        return builder()
                .with(MarketingEmployeeInfo::getId, marketingEmployee.getId())
                .with(MarketingEmployeeInfo::getCode, marketingEmployee.getCode())
                .with(MarketingEmployeeInfo::getDesignation, marketingEmployee.getDesignation().name())
                .with(MarketingEmployeeInfo::getName, marketingEmployee.getName())
                .with(MarketingEmployeeInfo::getAddress, marketingEmployee.getAddress())
                .with(MarketingEmployeeInfo::getPhoneNumber, marketingEmployee.getPhoneNumber());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
