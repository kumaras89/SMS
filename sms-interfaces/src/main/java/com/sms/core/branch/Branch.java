package com.sms.core.branch;


import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SMS_MA_BRANCH")
public class Branch extends BaseModel {
    @NotNull(message = "Branch Code is empty")
    @Size(min = 1, message = "Branch Code is empty")
    @Column(name = "BR_CODE", unique = true)
    private String code;

    @NotNull(message = "Branch Name is empty")
    @Size(min = 2, message = "Branch Name is empty")
    @Column(name = "BR_NAME")
    private String name;

    @NotNull(message = "isActive is not set")
    @Column(name = "BR_ACTIVE")
    private Integer isActive;

    @Valid
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "BR_ADDRESS_ID")
    private Address address;

    public Branch() {
        super();
    }

    public static Builder<Branch> builder() {
        return Builder.of(Branch.class);
    }

    public static Builder<Branch> toBuilder(final Branch branch) {
        return builder()
                .on(b -> b.getId()).set(branch.getId())
                .on(b -> b.getCode()).set(branch.getCode())
                .on(b -> b.getName()).set(branch.getName())
                .on(b -> b.getIsActive()).set(branch.getIsActive())
                .on(b -> b.getAddress()).set(branch.getAddress());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Address getAddress() {
        return address;
    }


}