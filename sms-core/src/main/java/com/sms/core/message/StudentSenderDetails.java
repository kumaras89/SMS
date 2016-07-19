package com.sms.core.message;

import com.sms.core.IStudentPortalService;
import com.sms.core.marketing.MarketingEmployeeInfo;
import com.sms.core.student.StudentFacade;
import com.sms.core.student.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sathish on 7/16/2016.
 */
@Component("Enrolled Students")
public class StudentSenderDetails implements SenderDetailsProvider
{
    @Autowired
    private StudentFacade studentFacade;

    @Override
    public List<SenderDetails> createDetails()
    {
        return studentFacade.findAll()
                .stream()
                .map(this::getSenderDetails)
                .collect(Collectors.toList());
    }


    private SenderDetails getSenderDetails(StudentInfo studentInfo) {
        return new SenderDetails(studentInfo.getName(),studentInfo.getPhoneNumber());
    }
}
