package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Ram on 6/20/2016.
 */
@Entity
@Table(name = "SMS_TR_SUBJECT")
public class Subject extends BaseModel{

    private static final long serialVersionUID = 5945573733224049850L;

    @NotNull(message = "Subject is Empty")
    @Size(min = 1,message = "Subject is Empty")
    @Column(name = "SU_NAME")
    private String name;

    @Min(value = 1,message = "Total mark is empty")
    @Column(name = "SU_TOTAL_MARK")
    private Long totalMark;

    @Min(value = 1,message = "Secured Mark is empty")
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
