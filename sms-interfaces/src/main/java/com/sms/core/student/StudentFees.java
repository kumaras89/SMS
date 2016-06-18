package com.sms.core.student;

import com.sms.core.common.Builder;
import com.sms.core.scheme.Fees;
import com.sms.core.scheme.FeesInfo;

/**
 * Created by Ram on 18-06-2016.
 */

public class StudentFees extends Fees{
    public static Builder<StudentFees> builder() {
        return Builder.of(StudentFees.class);
    }

    public static Builder<StudentFees> toBuilder(final FeesInfo feesInfo) {
        return builder().on(s -> s.getAmount()).set(feesInfo.getAmount());
    }
}
