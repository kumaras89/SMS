package com.sms.core.student;
import com.sms.core.IStudentScholarService;
import com.sms.core.StudentScholarBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by sathish on 6/20/2016.
 */
@RestController
@RequestMapping("/studentScholar")
public class StudentScholarRestController extends StudentScholarBaseController<StudentScholarInfo>
{
    @Autowired
    public StudentScholarRestController(final IStudentScholarService<StudentScholarInfo> studentScholarEnrollment)
    {
        super(studentScholarEnrollment);
    }
}

