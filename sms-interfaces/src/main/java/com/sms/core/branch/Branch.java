package com.sms.core.branch;


import com.sms.core.BaseModel;
import com.sms.core.common.Builder;
import com.sms.core.student.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "SMS_MA_BRANCH")
@SequenceGenerator(sequenceName = "SMS_SQ_BR",name = "SMS_SQ_BR" , allocationSize = 1)
public class Branch  implements Serializable{

    /**
     * <p>
     * Field names constants used in DAOs <br>
     * and Column names constants used in ORM annotations
     * </p>
     */

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5418291380983718474L;

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_BR")
    protected Long id;


    @NotNull(message = "Branch Code is empty")
    @Size(min = 1, message = "Branch Code is empty")
    @Column(name = "BR_CODE",unique = true)
    private String code;

    @NotNull(message = "Branch Name is empty")
    @Size(min = 1, message = "Branch Name is empty")
    @Column(name = "BR_NAME")
    private String name;

    @NotNull(message = "isActive is not set")
    @Column(name = "BR_ACTIVE")
    private Integer isActive;


    @NotNull(message = "Addresss is empty")
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

    public Long getId() {
        return id;
    }
}