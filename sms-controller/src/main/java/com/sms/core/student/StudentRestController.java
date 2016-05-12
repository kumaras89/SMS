package com.sms.core.student;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentRestController extends BaseController<StudentInfo> {

    @Autowired
    public StudentRestController(final IStudentPortalService<StudentInfo> studentEnrollmentService) {
        super(studentEnrollmentService);
    }
}
