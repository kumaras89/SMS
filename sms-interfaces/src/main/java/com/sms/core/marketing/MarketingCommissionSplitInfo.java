package com.sms.core.marketing;

import com.sms.core.common.Builder;

import java.math.BigDecimal;

/**
 * Created by sathish on 9/26/2016.
 */
public class MarketingCommissionSplitInfo
{
    private long id;

    private String studentNameAppNo;

    private String referencePersonNameCode;

    private BigDecimal amount;


    public MarketingCommissionSplitInfo() {

    }

    public static Builder<MarketingCommissionSplitInfo> builder() {
        return Builder.of(MarketingCommissionSplitInfo.class);
    }

    public static Builder<MarketingCommissionSplitInfo> toBuilder(final MarketingCommissionSplit commissionSplit) {
        return builder()
                .on(MarketingCommissionSplitInfo::getId).set(commissionSplit.getId())
                .on(MarketingCommissionSplitInfo::getReferencePersonNameCode).set(commissionSplit.getReferencePerson().getName()+"["+commissionSplit.getReferencePerson().getCode()+"]")
                .on(MarketingCommissionSplitInfo::getStudentNameAppNo).set(commissionSplit.getStudent().getName()+"["+commissionSplit.getStudent().getCode()+"]")
                .on(MarketingCommissionSplitInfo::getAmount).set(commissionSplit.getAmount());
    }

    public long getId() {
        return id;
    }

    public String getStudentNameAppNo() {
        return studentNameAppNo;
    }

    public String getReferencePersonNameCode() {
        return referencePersonNameCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
