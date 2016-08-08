package com.sms.core.payment;

import com.sms.core.common.Builder;
import com.sms.core.common.Reader;
import com.sms.core.feesparticular.Fees;
import com.sms.core.feesparticular.FeesInfo;
import com.sms.core.repositery.FeesParticularRepository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ram on 18-06-2016.
 */
@Entity
@Table(name = "SMS_TR_PAYMENT_FEES")
@SequenceGenerator(sequenceName = "SMS_SQ_PF",name = "SMS_SQ_PF",allocationSize = 1)
public class PaymentFees extends Fees implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SMS_SQ_PF")
    protected Long id;

    public Long getId() {
        return id;
    }

    public static Builder<PaymentFees> builder() {
        return Builder.of(PaymentFees.class);
    }

    public static Builder<PaymentFees> toBuilder(final FeesInfo feesInfo) {
        return builder().on(s -> s.getAmount()).set(feesInfo.getAmount());
    }

    public static Reader<FeesParticularRepository, PaymentFees> build(final FeesInfo feesInfo) {
        return Reader.of(repo -> builder()
                .on(PaymentFees::getAmount).set(feesInfo.getAmount())
                .on(PaymentFees::getFeesParticular).set(repo.findByCodeIgnoreCase(feesInfo.getFeesParticularCode()))
                .build());
    }


}
