package com.sms.core.student;

import com.sms.core.common.Builder;

import javax.persistence.*;

/**
 * Created by Ram on 6/20/2016. <P></>
 */
@Entity
@Table(name = "SMS_TR_OTHER_LANGUAGE")
public class OtherLanguage {

    @Id
    @GeneratedValue
    private Long id;

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

    public static Builder<OtherLanguage> builder() {
        return Builder.of(OtherLanguage.class);
    }
}
