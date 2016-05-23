package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "SMS_TR_GUARDIAN")
public class Guardian extends BaseModel {

    @Column(name = "GU_IS_EMPLOYED")
    private int isEmployed;

    @Column(name = "GU_NAME")
    private String name;

    @Column(name = "GU_OCCUPATION")
    private String occupation;

    @Column(name = "GU_MONTHLY_INCOME")
    private BigDecimal monthlyIncome;

    @Column(name = "GU_ANNUAL_INCOME")
    private BigDecimal annualIncome;

    @Column(name = "GU_GENDER")
    private Gender gender;

    @Column(name = "GU_RELATION")
    private Relation relationShip;

    @Column(name = "GU_PHONE_NUMBER")
    private String phoneNumber;

    public Guardian() {
    }

    private Guardian(final Builder builder) {

        super(builder);
        this.isEmployed = builder.isEmployed.get();
        this.name = builder.name.get();
        this.occupation = builder.occupation.get();
        this.monthlyIncome = builder.monthlyIncome.get();
        this.annualIncome = builder.annualIncome.get();
        this.gender = builder.gender.get();
        this.relationShip = builder.relationShip.get();
        this.phoneNumber = builder.phoneNumber.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getIsEmployed() {
        return isEmployed;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public Gender getGender() {
        return gender;
    }

    public Relation getRelationShip() {
        return relationShip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder extends BaseModel.Builder<Guardian, Builder> {

        private Optional<Integer> isEmployed = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<String> occupation = Optional.empty();
        private Optional<BigDecimal> monthlyIncome = Optional.empty();
        private Optional<BigDecimal> annualIncome = Optional.empty();
        private Optional<Gender> gender = Optional.empty();
        private Optional<Relation> relationShip = Optional.empty();
        private Optional<String> phoneNumber = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withIsEmployed(final Integer isEmployed) {
            this.isEmployed = Optional.of(isEmployed);
            return this;
        }

        public Builder withName(final String theName) {
            this.name = Optional.of(theName);
            return this;
        }

        public Builder withOccupation(final String theOccupation) {
            this.occupation = Optional.of(theOccupation);
            return this;
        }

        public Builder withMonthlyIncome(final BigDecimal theMonthlyIncome) {
            this.monthlyIncome = Optional.of(theMonthlyIncome);
            return this;
        }

        public Builder withAnnualIncome(final BigDecimal theAnnualIncome) {
            this.annualIncome = Optional.of(theAnnualIncome);
            return this;
        }

        public Builder withGender(final Gender theGender) {
            this.gender = Optional.of(theGender);
            return this;
        }

        public Builder withRelationShip(final Relation theRelationShip) {
            this.relationShip = Optional.of(theRelationShip);
            return this;
        }

        public Builder withPhoneNumber(final String thePhoneNumber) {
            this.phoneNumber = Optional.of(thePhoneNumber);
            return this;
        }

        public Guardian build() {
            return new Guardian(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
