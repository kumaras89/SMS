package com.sms.core.feesparticular;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Fees implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


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