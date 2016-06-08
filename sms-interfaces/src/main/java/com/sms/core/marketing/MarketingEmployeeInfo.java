package com.sms.core.marketing;

import com.sms.core.common.Builder;
import com.sms.core.student.Address;

public class MarketingEmployeeInfo {

    private Long id;
    private String code;
    private String name;
    private String designation;
    private Address address;
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
