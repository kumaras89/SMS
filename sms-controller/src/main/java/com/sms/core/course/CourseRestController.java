package com.sms.core.course;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseRestController extends BaseController<CourseInfo> {

    @Autowired
    public CourseRestController(final IStudentPortalService<CourseInfo> courseService) {
        super(courseService);
    }
}
