package com.sms.core.marketing;

import com.sms.core.BaseModel;
import com.sms.core.admin.User;
import com.sms.core.common.Builder;
import com.sms.core.common.Designation;
import com.sms.core.student.Address;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@Table(name = "SMS_TR_MARKETING_EMPLOYEE")
@SequenceGenerator(sequenceName = "SMS_SQ_ME",name = "SMS_SQ_ME")
public class MarketingEmployee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_ME")
    protected Long id;

    @Column(name = "ME_CODE", unique = true)
    private String code;

    @Column(name = "ME_NAME")
    private String name;

    @Column(name = "ME_DESIGNATION")
    private Designation designation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ME_ADDRESS_ID")
    private Address address;

    @Column(name = "ME_PHONE_NUMBER")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "ME_USER_ID")
    private User linkedUser;

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

    public User getLinkedUser() {
        return linkedUser;
    }

    public Long getId() {
        return id;
    }
}
