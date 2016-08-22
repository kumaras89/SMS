package com.sms.core.batch;

import com.sms.core.common.Builder;
import com.sms.core.course.Course;
import com.sms.core.course.CourseInfo;

import java.util.Date;

/**
 * Created by Assaycr-04 on 8/19/2016.
 */
public class BatchInfo {


    private long id;

    private String name;

    private Date durationFrom;

    private Date durationTo;

    private String courseCode;


    public long getId() { return id;   }

    public String getName() { return name;  }

    public Date getDurationFrom() {  return durationFrom;}

    public Date getDurationTo() {return durationTo; }

    public String getCourseCode() {return courseCode; }

    public static Builder<BatchInfo> builder() {
        return Builder.of(BatchInfo.class);
    }

    public static Builder<BatchInfo> toBuilder(final Batch batch) {
        return builder()
                .with(BatchInfo::getId, batch.getId())
                .with(BatchInfo::getName, batch.getName())
                .with(BatchInfo::getDurationFrom, batch.getDurationFrom())
                .with(BatchInfo::getDurationTo, batch.getDurationTo())
                .with(BatchInfo::getCourseCode, batch.getCourse().getCode());
    }




}
