package com.sms.core.course;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SMS_MA_COURSE")
@SequenceGenerator(name ="SMS_SQ_CO" ,sequenceName = "SMS_SQ_CO",allocationSize = 1)
public class Course  implements Serializable
{

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SQ_CO")
    protected Long id;



    @Column(name = "CO_CODE", unique = true)
    private String code;


    @Column(name = "CO_NAME")
    private String name;


    @Column(name = "CO_DESCRIPTION")
    private String description;

    public Course() {
    }


    public static Builder<Course> builder() {
        return Builder.of(Course.class);
    }

    public static Builder<Course> toBuilder(final CourseInfo course) {
        return builder()
                .on(c -> c.getName()).set(course.getName())
                .on(c -> c.getCode()).set(course.getCode())
                .on(c -> c.getDescription()).set(course.getDescription());
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }
}