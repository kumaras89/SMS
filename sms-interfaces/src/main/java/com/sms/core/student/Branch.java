package com.sms.core.student;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "sp_ma_branch")
public class Branch extends BaseModel {

    @Column(name = "br_code")
    private String code;

    @Column(name = "br_name")
    private String name;

    @Column(name = "br_active")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "br_address_id")
    @Cascade(value = CascadeType.ALL)
    private Address address;

    public Branch() {
        super();
    }

    private Branch(final Builder builder) {
        super(builder);
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.isActive = builder.isActive;
        this.address = builder.address.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final Branch branch) {
        return builder()
                .withId(branch.getId())
                .withCode(branch.getCode())
                .withIsActive(branch.getIsActive())
                .withAddress(branch.getAddress());
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

    public static class Builder extends BaseModel.Builder<Branch, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Integer isActive = 0;
        private Optional<Address> address = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withCode(final String theCode) {
            this.code = Optional.of(theCode);
            return this;
        }

        public Builder withName(final String theName) {
            this.name = Optional.of(theName);
            return this;
        }

        public Builder withIsActive(final int isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withAddress(final Address theAddress) {
            this.address = Optional.of(theAddress);
            return this;
        }

        public Branch build() {
            return new Branch(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}