package com.sms.core.feesparticular;

import com.sms.core.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "SMS_MA_FEES_PARTICULAR")
public class FeesParticular extends BaseModel {

    @Column(name = "FP_CODE", unique = true)
    private String code;

    @Column(name = "FP_NAME")
    private String name;

    public FeesParticular() {
        super();
    }

    public FeesParticular(Builder builder) {
        super(builder);
        this.code = builder.code.get();
        this.name = builder.name.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final FeesParticular feesParticular) {
        return builder()
                .withCode(feesParticular.getCode())
                .withName(feesParticular.getName());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static class Builder extends BaseModel.Builder<FeesParticular, Builder> {

        private Optional<String> code = Optional.empty();
        private Optional<String> name = Optional.empty();

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

        public FeesParticular build() {
            return new FeesParticular(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
