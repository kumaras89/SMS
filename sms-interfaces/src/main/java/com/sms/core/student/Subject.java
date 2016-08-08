package com.sms.core.student;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Ram on 6/20/2016.
 */
@Entity
@Table(name = "SMS_TR_SUBJECT")
@SequenceGenerator(sequenceName = "SMS_SQ_SU",name = "SMS_SQ_SU",allocationSize = 1)
public class Subject implements Serializable{

    private static final long serialVersionUID = 5945573733224049850L;


    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_SU")
    protected Long id;

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

    public Long getId() {
        return id;
    }

    public static Builder<Subject> builder() {
        return Builder.of(Subject.class);
    }
}
