package com.sms.core.course;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends BaseServiceConvertorImpl<CourseInfo, Course> {

    @Autowired
    public CourseServiceImpl(final CourseRepository courseRepository) {

        super(courseRepository,
                (courseInfo) ->
                        Course.toBuilder(courseInfo).build(),
                (course) -> CourseInfo.toBuilder(course).build());
    }

    @Override
    protected Course buildToPersistObject(Long id, CourseInfo courseInfo) {

        return Course.toBuilder(courseInfo)
                .withId(id)
                .build();
    }
}