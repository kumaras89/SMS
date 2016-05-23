package com.sms.core.scheme;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "SMS_MA_FEES_CATEGORY")
public class FeesCategory extends BaseModel {

    @Column(name = "FC_CODE", unique = true)
    private String code;

    @Column(name = "FC_NAME")
    private String name;

    @Column(name = "FC_WEIGHTAGE")
    private int weightage = 0;

    public FeesCategory() {
        super();
    }

    public FeesCategory(Builder builder) {
        super(builder);
        this.code = builder.code.get();
        this.name = builder.name.get();
        this.weightage = builder.weightage.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final FeesCategoryInfo feesCategoryInfo) {
        return builder()
                .withCode(feesCategoryInfo.getCode())
                .withName(feesCategoryInfo.getName())
                .withWeightage(feesCategoryInfo.getWeightage());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getWeightage() {
        return weightage;
    }

    public static class Builder extends BaseModel.Builder<FeesCategory, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();
        private Optional<Integer> weightage = Optional.empty();

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

        public Builder withWeightage(final Integer theWeightage) {
            this.weightage = Optional.of(theWeightage);
            return this;
        }

        public FeesCategory build() {
            return new FeesCategory(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
