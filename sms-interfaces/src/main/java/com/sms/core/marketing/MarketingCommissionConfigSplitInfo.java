package com.sms.core.marketing;

import com.sms.core.common.Builder;
import com.sms.core.common.Designation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 17:58.
 *  
 */
public class MarketingCommissionConfigSplitInfo implements Serializable {
    private static final long serialVersionUID = -8392018822491242239L;
    private Long id;
    private Designation subOrdinate;
    private Designation superior;
    private BigDecimal amount;

    public static Builder<MarketingCommissionConfigSplitInfo> builder() {
        return Builder.of(MarketingCommissionConfigSplitInfo.class);
    }

    public static Builder<MarketingCommissionConfigSplitInfo> toBuilder(final MarketingCommissionSplitConfig
        marketingCommissionSplitConfigInfo) {
        return builder()
            .with(MarketingCommissionConfigSplitInfo::getId, marketingCommissionSplitConfigInfo.getId())
            .with(MarketingCommissionConfigSplitInfo::getSubOrdinate, marketingCommissionSplitConfigInfo.getSubOrdinate())
            .with(MarketingCommissionConfigSplitInfo::getSuperior, marketingCommissionSplitConfigInfo.getSuperior())
            .with(MarketingCommissionConfigSplitInfo::getAmount, marketingCommissionSplitConfigInfo.getAmount());
    }

    public Long getId() {
        return id;
    }

    public Designation getSubOrdinate() {
        return subOrdinate;
    }

    public Designation getSuperior() {
        return superior;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
