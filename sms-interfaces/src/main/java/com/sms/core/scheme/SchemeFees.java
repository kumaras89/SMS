package com.sms.core.scheme;

import com.sms.core.common.Builder;
import com.sms.core.feesparticular.Fees;
import com.sms.core.feesparticular.FeesInfo;

import javax.persistence.*;

/**
 * Created by Ram on 17-06-2016.
 * <p></p>
 */
@Entity
@Table(name = "SMS_TR_SCHEME_TO_FEES")
@SequenceGenerator(name = "SMS_SQ_SF",sequenceName ="SMS_SQ_SF" , allocationSize = 1)
public class SchemeFees extends Fees {
    private static final long serialVersionUID = -6699305076957421496L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_SF")
    protected Long id;

    public Long getId() {
        return id;
    }

    public static Builder<SchemeFees> builder() {
        return Builder.of(SchemeFees.class);
    }

    public static Builder<SchemeFees> toBuilder(final FeesInfo feesInfo) {
        return builder().on(s -> s.getAmount()).set(feesInfo.getAmount());
    }
}
