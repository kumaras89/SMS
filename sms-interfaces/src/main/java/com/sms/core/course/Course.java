package com.sms.core.course;

import com.sms.core.BaseModel;
import com.sms.core.util.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_MA_COURSE")
public class Course extends BaseModel {

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


}