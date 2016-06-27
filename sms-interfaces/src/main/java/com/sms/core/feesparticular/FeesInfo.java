package com.sms.core.feesparticular;

import com.sms.core.common.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class FeesInfo {

    private Long id;

    @NotNull(message = "Fee Particular Code is empty")
    @Size(min = 1, message = "Fee Particular is empty")
    private String feesParticularCode;

    @NotNull(message = "Amount is Empty")
    private BigDecimal amount;

    public FeesInfo() {
    }

    public FeesInfo(String feesParticularCode, BigDecimal amount) {
        this.feesParticularCode = feesParticularCode;
        this.amount = amount;
    }

    public static Builder<FeesInfo> builder() {
        return Builder.of(FeesInfo.class);
    }

    public static Builder<FeesInfo> toBuilder(final Fees fees) {
        return builder()
                .with(FeesInfo::getId, fees.getId())
                .with(FeesInfo::getFeesParticularCode, fees.getFeesParticular().getCode())
                .with(FeesInfo::getAmount, fees.getAmount());
    }

    public static FeesInfo build(Fees fees) {
        return toBuilder(fees).build();
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
