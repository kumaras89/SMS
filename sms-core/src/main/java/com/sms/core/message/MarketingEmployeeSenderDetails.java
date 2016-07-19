package com.sms.core.message;

import com.sms.core.IStudentPortalService;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.marketing.MarketingEmployeeInfo;
import com.sms.core.scholarship.StudentScholarService;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sathish on 7/16/2016.
 */
@Component("Marketing Employees")
public class MarketingEmployeeSenderDetails implements SenderDetailsProvider
{
    @Autowired
    private IStudentPortalService<MarketingEmployeeInfo> iStudentPortalService;


    @Override
    public List<SenderDetails> createDetails() {
        return iStudentPortalService.findAll()
                .stream()
                .map(this::getSenderDetails)
                .collect(Collectors.toList());
    }

    private SenderDetails getSenderDetails(MarketingEmployeeInfo marketingEmployeeInfo) {
        return new SenderDetails(marketingEmployeeInfo.getName(),marketingEmployeeInfo.getPhoneNumber());
    }
}
