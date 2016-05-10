package com.sms.core.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.sms.core.BaseController;
import com.sms.core.IStudentPortalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentRestController extends BaseController<Student> {

    @Autowired
    public StudentRestController(final IStudentPortalService<Student> studentEnrollmentService) {
        super(studentEnrollmentService);
    }
}
