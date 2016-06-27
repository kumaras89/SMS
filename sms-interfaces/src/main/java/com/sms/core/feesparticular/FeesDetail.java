package com.sms.core.feesparticular;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 25/06/16.
 */
public class FeesDetail {

    public static FeesDetail EMPTY = new FeesDetail(BigDecimal.ZERO, new ArrayList<>());

    private BigDecimal amount;
    private List<FeesInfo> detailedFees;



    public FeesDetail() {
    }

    public FeesDetail(BigDecimal amount, List<FeesInfo> detailedFees) {
        this.amount = amount;
        this.detailedFees = detailedFees;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<FeesInfo> getDetailedFees() {
        return detailedFees;
    }
}
