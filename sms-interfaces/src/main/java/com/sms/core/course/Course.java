package com.sms.core.course;

import com.sms.core.BaseModel;
import com.sms.core.common.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SMS_MA_COURSE")
public class Course extends BaseModel {

    @NotNull(message = "Course Code is empty")
    @Size(min = 1, message = "Course Code is empty")
    @Column(name = "CO_CODE", unique = true)
    private String code;

    @NotNull(message = "Course name is empty")
    @Size(min = 1, message = "Course name is empty")
    @Column(name = "CO_NAME")
    private String name;

    @NotNull(message = "Course Description is empty")
    @Size(min = 1, message = "Course Description is empty")
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