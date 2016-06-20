package com.sms.core.scheme;

import com.sms.core.common.Builder;

import java.math.BigDecimal;

public class FeesInfo {

    private Long id;
    private String feesParticularCode;
    private BigDecimal amount;


    public static Builder<FeesInfo> builder() {
        return Builder.of(FeesInfo.class);
    }

    public static Builder<FeesInfo> toBuilder(final Fees fees) {
        return builder()
                .with(FeesInfo::getId, fees.getId())
                .with(FeesInfo::getFeesParticularCode, fees.getFeesParticular().getCode())
                .with(FeesInfo::getAmount, fees.getAmount());
    }

    public String getFeesParticularCode() {
        return feesParticularCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }


}
