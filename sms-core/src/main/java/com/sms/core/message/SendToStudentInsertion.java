package com.sms.core.message;

import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.repositery.MarketingEmployeeRepository;
import com.sms.core.repositery.MessageRepository;
import com.sms.core.student.StudentScholarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sathish on 8/11/2016.
 */

@Component
public class SendToStudentInsertion implements SendToAllImp
{
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MarketingEmployeeRepository marketingEmployeeRepository;

    @Autowired
    private SMSConfig smsConfig;

    @Override
    public void sendAll(final SendingDetails sendingDetails)
    {
        SendSmsToGuardian(sendingDetails);
        SendSmsToMarket(sendingDetails);
        SendSmsToStudent(sendingDetails);
    }

    public void SendSmsToStudent(final SendingDetails sendingDetails)
    {
        final Message message = messageRepository.findById(1);
        final String welcomeMessage = "Hi "+sendingDetails.getStudentName()+",Application No:"+sendingDetails.getStudentApplicationNumber()+","+message.getMessage();
        SendMesssage(sendingDetails.getStudentPhoneNumber(),sendingDetails.getStudentName(),welcomeMessage);
    }

    public void SendSmsToMarket(final SendingDetails sendingDetails)
    {
        final Message message = messageRepository.findById(2);
        final String welcomeMessage = "Hi "+sendingDetails.getMarketingEmployeeName()+",Name:"+sendingDetails.getStudentName()+",Application Number:"+sendingDetails.getStudentApplicationNumber()+message.getMessage();
        SendMesssage(sendingDetails.getMarketingEmployeePhonenumber(),sendingDetails.getStudentName(),welcomeMessage);
    }

    public void SendSmsToGuardian(final SendingDetails sendingDetails)
    {
        final Message message = messageRepository.findById(1);
        final String welcomeMessage = "Hi "+sendingDetails.getStudentName()+",Application No:"+sendingDetails.getStudentApplicationNumber()+","+message.getMessage();
        SendMesssage(sendingDetails.getGuardianPhoneNumber(),sendingDetails.getStudentName(),welcomeMessage);
    }

    public void SendMesssage(String phoneNumber, String studentName,String welcomeMessage)
    {
        SMSSender.sendSms(SMSDetails.builder().on(SMSDetails::getName).set(studentName)
                .on(SMSDetails::getPhoneNumber).set(phoneNumber)
                .on(SMSDetails::getMessage).set(welcomeMessage).build()).apply(smsConfig);
    }
}
