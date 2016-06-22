package com.sms.core.student;

import com.sms.core.common.Builder;
import com.sms.core.feesparticular.FeesParticular;
import com.sms.core.scheme.Fees;
import com.sms.core.scheme.FeesInfo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.function.Function;

/**
 * Created by Ram on 18-06-2016. <p></p>
 */
@Entity
@Table(name = "SMS_TR_STUDENT_FEES")
public class StudentFees extends Fees {
    private static final long serialVersionUID = 9220694021987796819L;

    public static Builder<StudentFees> builder() {
        return Builder.of(StudentFees.class);
    }

    public static Function<FeesParticular,Builder<StudentFees>> toBuilder(final FeesInfo feesInfo) {
        return (feesParticular) ->builder().on(s -> s.getAmount()).set(feesInfo.getAmount()).with
            (StudentFees::getFeesParticular, feesParticular);
    }
}
