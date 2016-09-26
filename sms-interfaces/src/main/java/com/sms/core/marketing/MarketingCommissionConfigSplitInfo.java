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
    private String subOrdinate;
    private String superior;
    private BigDecimal amount;

    public static Builder<MarketingCommissionConfigSplitInfo> builder() {
        return Builder.of(MarketingCommissionConfigSplitInfo.class);
    }

    public static Builder<MarketingCommissionConfigSplitInfo> toBuilder(final MarketingCommissionSplitConfig
                                                                                marketingCommissionSplitConfigInfo) {
        return builder()
                .with(MarketingCommissionConfigSplitInfo::getId, marketingCommissionSplitConfigInfo.getId())
                .with(MarketingCommissionConfigSplitInfo::getSubOrdinate, marketingCommissionSplitConfigInfo.getSubOrdinate().name())
                .with(MarketingCommissionConfigSplitInfo::getSuperior, marketingCommissionSplitConfigInfo.getSuperior().name())
                .with(MarketingCommissionConfigSplitInfo::getAmount, marketingCommissionSplitConfigInfo.getAmount());
    }

    public Long getId() {
        return id;
    }

    public String getSubOrdinate() {
        return subOrdinate;
    }

    public String getSuperior() {
        return superior;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
