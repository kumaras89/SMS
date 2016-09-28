package com.sms.core.marketing;

import com.sms.core.common.Builder;

import java.math.BigDecimal;

/**
 * Created by sathish on 9/26/2016.
 */
public class MarketingCommissionSplitInfo
{
    private long id;

    private String studentName;

    private String applicationNumber;

    private String referencePersonName;

    private String referencePersonCode;

    private BigDecimal amount;


    public MarketingCommissionSplitInfo() {

    }

    public static Builder<MarketingCommissionSplitInfo> builder() {
        return Builder.of(MarketingCommissionSplitInfo.class);
    }

    public static Builder<MarketingCommissionSplitInfo> toBuilder(final MarketingCommissionSplit commissionSplit) {
        return builder()
                .on(MarketingCommissionSplitInfo::getId).set(commissionSplit.getId())
                .on(MarketingCommissionSplitInfo::getReferencePersonName).set(commissionSplit.getReferencePerson().getName())
                .on(MarketingCommissionSplitInfo::getReferencePersonCode).set(commissionSplit.getReferencePerson().getCode())
                .on(MarketingCommissionSplitInfo::getStudentName).set(commissionSplit.getStudent().getName())
                .on(MarketingCommissionSplitInfo::getApplicationNumber).set(commissionSplit.getStudent().getCode())
                .on(MarketingCommissionSplitInfo::getAmount).set(commissionSplit.getAmount());
    }

    public long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getReferencePersonName() {
        return referencePersonName;
    }

    public String getReferencePersonCode() {
        return referencePersonCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
