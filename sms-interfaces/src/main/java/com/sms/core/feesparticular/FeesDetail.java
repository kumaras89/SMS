package com.sms.core.feesparticular;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ganesan on 25/06/16.
 */
public class FeesDetail {

    private BigDecimal amount;
    private List<FeesInfo> detailedFees;

    public BigDecimal getAmount() {
        return amount;
    }

    public List<FeesInfo> getDetailedFees() {
        return detailedFees;
    }
}
