package com.sms.core.scheme;

import com.sms.core.BaseModel;
import com.sms.core.feesparticular.FeesParticular;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "SMS_TR_SCHEME_TO_FEES")
public class SchemeFees extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "FC_FEES_PARTICULAR_ID")
    private FeesParticular feesParticular;

    @Column(name = "FC_WEIGHTAGE")
    private int weightage = 0;

    public SchemeFees() {
        super();
    }

    public SchemeFees(Builder builder) {
        super(builder);
        this.feesParticular = builder.feesParticular.get();
        this.weightage = builder.weightage.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final SchemeFeesInfo schemeFeesInfo) {
        return builder().withWeightage(schemeFeesInfo.getWeightage());
    }

    public int getWeightage() {
        return weightage;
    }

    public FeesParticular getFeesParticular() {
        return feesParticular;
    }

    public static class Builder extends BaseModel.Builder<SchemeFees, Builder> {

        private Optional<FeesParticular> feesParticular = Optional.empty();
        private Optional<Integer> weightage = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withFeesParticular(final FeesParticular theFeesParticular) {
            this.feesParticular = Optional.of(theFeesParticular);
            return this;
        }

        public Builder withWeightage(final Integer theWeightage) {
            this.weightage = Optional.of(theWeightage);
            return this;
        }

        public SchemeFees build() {
            return new SchemeFees(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
