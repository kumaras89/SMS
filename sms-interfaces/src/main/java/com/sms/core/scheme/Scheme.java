package com.sms.core.scheme;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "SMS_MA_SCHEME")
public class Scheme extends BaseModel {

    @Column(name = "SC_CODE", unique = true)
    private String code;

    @Column(name = "SC_NAME")
    private String name;

    @Column(name = "SC_FEES_AMOUNT")
    private BigDecimal feesAmount;

    @Column(name = "SC_DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FC_SCHEME_ID")
    private Set<SchemeFees> schemeFees;

    public Scheme() {
        super();
    }

    public static Builder<Scheme> builder() {
        return Builder.of(Scheme.class);
    }

    public static Builder<Scheme> toBuilder(final SchemeInfo schemeInfo) {
        return builder()
                .with(Scheme::getName, schemeInfo.getName())
                .with(Scheme::getCode, schemeInfo.getCode())
                .with(Scheme::getDescription, schemeInfo.getDescription())
                .with(Scheme::getFeesAmount, schemeInfo.getFeesAmount());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<SchemeFees> getSchemeFees() {
        return schemeFees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }


}
