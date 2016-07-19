package com.sms.core.message;

import com.sms.core.IStudentPortalService;
import com.sms.core.marketing.MarketingEmployeeInfo;
import com.sms.core.repositery.MessageRepository;
import com.sms.core.scholarship.StudentScholarService;
import com.sms.core.student.StudentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
@Component
public class MessageConfig {

    @Autowired
    private MessageRepository messageRepository;

    @Resource(name = "senderDetailsProviders")
    private Map<String, SenderDetailsProvider> deatailProviders;

    @Autowired
    private IStudentPortalService<MarketingEmployeeInfo> marketingEmployeeService;

    @Autowired
    private StudentFacade studentFacade;

    @Autowired
    private StudentScholarService studentScholarService;

    @Autowired
    private SMSLoginDetails smsLoginDetails;


    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public Map<String, SenderDetailsProvider> getDetailsProviders() {
        return deatailProviders;
    }

    public SMSLoginDetails getSmsLoginDetails() {
        return smsLoginDetails;
    }

    public IStudentPortalService<MarketingEmployeeInfo> getMarketingEmployeeService() {
        return marketingEmployeeService;
    }

    public StudentFacade getStudentFacade() {
        return studentFacade;
    }

    public StudentScholarService getStudentScholarService() {
        return studentScholarService;
    }
}
