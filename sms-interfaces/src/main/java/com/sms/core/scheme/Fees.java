package com.sms.core.scheme;

import com.sms.core.feesparticular.FeesParticular;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Fees implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "FC_FEES_PARTICULAR_ID")
    private FeesParticular feesParticular;

    @Column(name = "FC_AMOUNT")
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

    public Long getId() {
        return id;
    }
}
