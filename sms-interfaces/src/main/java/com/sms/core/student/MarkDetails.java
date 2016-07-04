package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.sound.midi.Sequence;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Ram on 6/20/2016. <p></p>
 */
@Entity
@Table(name = "SMS_TR_MARK_DETAILS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CLASS")
@SequenceGenerator(name = "SMS_SQ_MD",sequenceName = "SMS_SQ_MD")
public class MarkDetails implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SMS_SQ_MD")
    @Column(name = "MD_ID")
    private Long id;

    @NotNull(message = "Subject Details is empty")
    @Valid
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "SU_MD_ID")
    private Set<Subject> subjects;

    @NotNull(message = "Additional education details is empty")
    @Valid
    @Embedded
    private MarkAdditionalDetails additionalDetails;

    @Min(value = 1, message = "Total Mark is empty")
    @Column(name = "MD_TOTAL_MARKS")
    private Long totalMarks;

    @Min(value = 1,message = "Percentage is empty")
    @Max(value = 100, message = "Percentage is invalid")
    @Column(name = "MD_PERCENTAGE")
    private int percentage;


    public Set<Subject> getSubjects() {
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

    public Long getId() {
        return id;
    }

    public static Builder<MarkDetails> builder() {
        return Builder.of(MarkDetails.class);
    }
}
