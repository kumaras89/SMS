package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.feesparticular.Fees;
import com.sms.core.feesparticular.FeesInfo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ram on 18-06-2016.
 */
@Entity
@Table(name = "SMS_TR_PAYMENT_FEES")
public class PaymentFees extends Fees {
    public static Builder<PaymentFees> builder() {
        return Builder.of(PaymentFees.class);
    }

    public static Builder<PaymentFees> toBuilder(final FeesInfo feesInfo) {
        return builder().on(s -> s.getAmount()).set(feesInfo.getAmount());
    }
}
