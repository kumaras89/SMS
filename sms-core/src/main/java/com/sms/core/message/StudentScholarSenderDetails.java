package com.sms.core.message;

import com.sms.core.scholarship.StudentScholarService;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sathish on 7/16/2016.
 */
@Component("Scholarship Students")
public class StudentScholarSenderDetails implements SenderDetailsProvider{

    @Autowired
    private StudentScholarService studentScholarService;

    @Override
    public List<SenderDetails> createDetails() {
        return studentScholarService.findAll()
                .stream()
                .map(this::getSenderDetails)
                .collect(Collectors.toList());
    }

    private SenderDetails getSenderDetails(StudentScholarInfo studentScholarInfo) {
        return new SenderDetails(studentScholarInfo.getName(),studentScholarInfo.getStudentPhoneNumber());
    }
}
