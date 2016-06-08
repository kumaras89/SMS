package com.sms.core.marketing;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.common.Designation;
import com.sms.core.student.Address;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "SMS_TR_MARKETING_EMPLOYEE")
public class MarketingEmployee extends BaseModel {

    @Column(name = "ME_CODE")
    private String code;

    @Column(name = "ME_NAME")
    private String name;

    @Column(name = "ME_DESIGNATION")
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "ME_ADDRESS_ID")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address;

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
