package com.sms.core.scheme;

import com.sms.core.BaseModel;
import com.sms.core.feesparticular.FeesParticular;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Fees extends BaseModel {

    @Valid
    @ManyToOne
    @JoinColumn(name = "FC_FEES_PARTICULAR_ID")
    private FeesParticular feesParticular;

    @Column(name = "FC_WEIGHTAGE")
    private BigDecimal amount;

    public Fees() {
        super();
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public FeesParticular getFeesParticular() {
        return feesParticular;
    }


}
