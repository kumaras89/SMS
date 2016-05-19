package com.sms.core.repositery;

import com.sms.core.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCodeIgnoreCase(final String code);

}
