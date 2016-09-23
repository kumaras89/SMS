package com.sms.core.batch;

import com.sms.core.common.Builder;
import com.sms.core.course.Course;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SMS_TR_BATCH")
@SequenceGenerator(name = "SMS_SQ_BT", sequenceName = "SMS_SQ_BT", allocationSize = 1)
public class Batch implements Serializable {

    private static final long serialVersionUID = -512520987716910408L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_BT")
    private long id;

    @Column(name = "BATCH_NAME", unique = true)
    private String name;

    @Column(name = "BATCH_DURATION_FROM")
    private Date durationFrom;

    @Column(name = "BATCH_DURATION_TO")
    private Date durationTo;

    @ManyToOne
    @JoinColumn(name = "BATCH_COURSE_ID")
    private Course course;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getDurationTo() {
        return durationTo;
    }

    public Course getCourse() {
        return course;
    }


    public static Builder<Batch> builder() {
        return Builder.of(Batch.class);
    }

    public static Builder<Batch> toBuilder(final BatchInfo batchInfo) {
        return builder()
            .with(Batch::getId, batchInfo.getId())
            .with(Batch::getName, batchInfo.getName())
            .with(Batch::getDurationFrom, batchInfo.getDurationFrom())
            .with(Batch::getDurationTo, batchInfo.getDurationTo());
    }
}
