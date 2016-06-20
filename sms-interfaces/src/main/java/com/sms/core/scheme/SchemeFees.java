package com.sms.core.scheme;

import com.sms.core.common.Builder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ram on 17-06-2016.
 */
@Entity
@Table(name = "SMS_TR_SCHEME_TO_FEES")
public class SchemeFees extends Fees {
    public static Builder<SchemeFees> builder() {
        return Builder.of(SchemeFees.class);
    }

    public static Builder<SchemeFees> toBuilder(final FeesInfo feesInfo) {
        return builder().on(s -> s.getAmount()).set(feesInfo.getAmount());
    }
}
