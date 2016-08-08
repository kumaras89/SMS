package com.sms.core.student;

import com.sms.core.common.Builder;
import com.sms.core.feesparticular.Fees;

import javax.persistence.*;

/**
 * Created by Ram on 18-06-2016. <p></p>
 */
@Entity
@Table(name = "SMS_TR_STUDENT_FEES")
@SequenceGenerator(sequenceName = "SMS_SQ_STF",name = "SMS_SQ_STF", allocationSize = 1)
public class StudentFees extends Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_STF")
    protected Long id;

    public Long getId() {
        return id;
    }

    private static final long serialVersionUID = 9220694021987796819L;

    public static Builder<StudentFees> builder() {
        return Builder.of(StudentFees.class);
    }

}
