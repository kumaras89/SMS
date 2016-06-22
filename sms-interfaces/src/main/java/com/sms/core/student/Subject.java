package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ram on 6/20/2016.
 */
@Entity
@Table(name = "SMS_TR_SUBJECT")
public class Subject extends BaseModel{

    private static final long serialVersionUID = 5945573733224049850L;
    @Column(name = "SU_NAME")
    private String name;
    @Column(name = "SU_TOTAL_MARK")
    private Long totalMark;
    @Column(name = "SU_SECURED_MARK")
    private Long securedMark;

    public String getName() {
        return name;
    }

    public Long getTotalMark() {
        return totalMark;
    }

    public Long getSecuredMark() {
        return securedMark;
    }

    public static Builder<Subject> builder() {
        return Builder.of(Subject.class);
    }
}
