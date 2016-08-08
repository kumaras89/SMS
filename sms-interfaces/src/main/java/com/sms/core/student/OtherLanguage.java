package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Ram on 6/20/2016. <P></>
 */
@Entity
@Table(name = "SMS_TR_OTHER_LANGUAGE")
@SequenceGenerator(sequenceName = "SMS_SQ_OL",name = "SMS_SQ_OL", allocationSize = 1)
public class OtherLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SMS_SQ_OL")
    private Long id;

    @NotNull(message = "Language name is empty")
    @Size(min = 1, message = "Language name is empty")
    @Column(name = "OL_NAME")
    private String name;


    @Column(name = "OL_READ")
    private boolean canRead;

    @Column(name = "OL_WRITE")
    private boolean canWrite;

    @Column(name = "OL_SPEAK")
    private boolean canSpeak;

    public String getName() {
        return name;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public boolean isCanSpeak() {
        return canSpeak;
    }

    public Long getId() {
        return id;
    }

    public static Builder<OtherLanguage> builder() {
        return Builder.of(OtherLanguage.class);
    }
}
