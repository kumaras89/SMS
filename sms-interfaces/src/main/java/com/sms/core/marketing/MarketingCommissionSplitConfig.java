package com.sms.core.marketing;

import com.sms.core.common.Builder;
import com.sms.core.common.Designation;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 17:52.
 *  
 */
@Entity
@Table(name = "SMS_MS_MARKETING_COMMISSION_CONFIG")
@SequenceGenerator(sequenceName = "SMS_SQ_MCC", name = "SMS_SQ_MCC", allocationSize = 1)
public class MarketingCommissionSplitConfig implements Serializable {
    private static final long serialVersionUID = -3543798088789449902L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_MCC")
    private Long id;

    @Column(name = "MCC_SUB_ORDINATE")
    private Designation subOrdinate;

    @Column(name = "MCC_SUPERIOR")
    private Designation superior;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public static Builder<MarketingCommissionSplitConfig> builder() {
        return Builder.of(MarketingCommissionSplitConfig.class);
    }

    public static Builder<MarketingCommissionSplitConfig> toBuilder(final MarketingCommissionConfigSplitInfo marketingCommissionConfigSplitInfo) {
        return builder()
            .with(MarketingCommissionSplitConfig::getId, marketingCommissionConfigSplitInfo.getId())
            .with(MarketingCommissionSplitConfig::getSubOrdinate, marketingCommissionConfigSplitInfo.getSubOrdinate())
            .with(MarketingCommissionSplitConfig::getSuperior, marketingCommissionConfigSplitInfo.getSuperior())
            .with(MarketingCommissionSplitConfig::getAmount, marketingCommissionConfigSplitInfo.getAmount());
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
