package com.sms.core.scheme;

import com.sms.core.BaseModel;
import com.sms.core.util.Builder;
import com.sms.core.feesparticular.FeesParticular;

import javax.persistence.*;

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

    public static Builder<SchemeFees> builder() {
        return Builder.of(SchemeFees.class);
    }

    public static Builder<SchemeFees> toBuilder(final SchemeFeesInfo schemeFeesInfo) {
        return builder().on(s -> s.getWeightage()).set(schemeFeesInfo.getWeightage());
    }

    public int getWeightage() {
        return weightage;
    }

    public FeesParticular getFeesParticular() {
        return feesParticular;
    }


}
