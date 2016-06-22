package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ram on 6/20/2016. <p></p>
 */
@Entity
@Table(name = "SMS_TR_MARK_DETAILS")
public class MarkDetails {

    @Id
    @GeneratedValue
    @Column(name = "MD_ID")
    private Long id;

    @OneToMany
    @JoinColumn(name = "SU_MD_ID")
    private List<Subject> subjects;
    @Embedded
    private MarkAdditionalDetails additionalDetails;
    @Column(name = "MD_TOTAL_MARKS")
    private Long totalMarks;
    @Column(name = "MD_PERCENTAGE")
    private int percentage;


    public List<Subject> getSubjects() {
        return subjects;
    }

    public MarkAdditionalDetails getAdditionalDetails() {
        return additionalDetails;
    }

    public Long getTotalMarks() {
        return totalMarks;
    }

    public int getPercentage() {
        return percentage;
    }

    public static Builder<MarkDetails> builder() {
        return Builder.of(MarkDetails.class);
    }
}
