package com.sms.core.message;

import com.sms.core.SmsException;
import com.sms.core.common.Builder;
import com.sms.core.common.FList;
import com.sms.core.marketing.MarketingEmployee;
import com.sms.core.marketing.MarketingEmployeeInfo;
import com.sms.core.repositery.*;
import com.sms.core.student.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by sathish on 7/12/2016.
 */
@Service
@Transactional
public class MessageServiceImp implements MessageService
{

    private final StudentScholarRepository studentScholarRepository;
    private final MarketingEmployeeRepository marketingEmployeeRepository;
    private final StudentRepository studentRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImp(final StudentScholarRepository studentScholarRepository, MarketingEmployeeRepository marketingEmployeeRepository,StudentRepository studentRepository,MessageRepository messageRepository) {
        this.studentScholarRepository = studentScholarRepository;
        this.marketingEmployeeRepository = marketingEmployeeRepository;
        this.studentRepository = studentRepository;
        this.messageRepository = messageRepository;
    }

    public void saveMesaging(MessageInfo messageInfo)
    {
        messageRepository.saveAndFlush(Message.toBuilder(messageInfo).build());
    }

    private static StudentScholarInfo scholarToInfo(StudentScholar source) {
        return StudentScholarInfo.toBuilder(source).build();
    }

    private static MarketingEmployeeInfo marketingToInfo(MarketingEmployee source)
    {
        return  MarketingEmployeeInfo.toBuilder(source).build();
    }

    private static StudentInfo studentToInfo(Student source)
    {
        return StudentEnrollmentConverter.convertTo(source);
    }

    private static MessageInfo messageToInfo(Message source) {return MessageInfo.toBuilder(source).build();}

    @Override
    public void sendMessageToAll(MessageInfo messageInfo)
    {
        saveMesaging(messageInfo);

        List<MessageContain> messageContains = new ArrayList<MessageContain>();

        if(messageInfo.getToReceiver().equals("Scholarship Students"))
        {
            List<StudentScholarInfo> studentScholarList= FList.of(this.studentScholarRepository.findAll()).map(MessageServiceImp::scholarToInfo).get();

            Iterator<StudentScholarInfo> itr = studentScholarList.iterator();
            while(itr.hasNext())
            {
                StudentScholarInfo inf = itr.next();
                MessageContain mg = new MessageContain();
                mg.setName(inf.getName());
                mg.setPhoneNumber(inf.getStudentPhoneNumber());
                messageContains.add(mg);
            }
            SendMessage.sendingMessageToAll(messageContains,messageInfo.getMessage());
        }
        else if(messageInfo.getToReceiver().equals("Marketing Employees"))
        {
            List<MarketingEmployeeInfo> marketingEmployeeList = FList.of(this.marketingEmployeeRepository.findAll()).map(MessageServiceImp::marketingToInfo).get();

            Iterator<MarketingEmployeeInfo> itr = marketingEmployeeList.iterator();

            while(itr.hasNext())
            {
                MarketingEmployeeInfo inf = itr.next();
                MessageContain mg = new MessageContain();
                mg.setName(inf.getName());
                mg.setPhoneNumber(inf.getPhoneNumber());
                messageContains.add(mg);
            }
            SendMessage.sendingMessageToAll(messageContains,messageInfo.getMessage());
        }else if(messageInfo.getToReceiver().equals("Enrolled Students"))
        {
            List<StudentInfo> studentList = FList.of(this.studentRepository.findAll()).map(MessageServiceImp::studentToInfo).get();

            Iterator<StudentInfo> itr = studentList.iterator();
            while(itr.hasNext())
            {
                StudentInfo inf = itr.next();
                MessageContain mg = new MessageContain();
                mg.setName(inf.getName());
                mg.setPhoneNumber(inf.getPhoneNumber());
                messageContains.add(mg);
            }
            SendMessage.sendingMessageToAll(messageContains,messageInfo.getMessage());
        }else
        {
            SendMessage.sendingMessageToParticular(messageInfo.getToReceiver(),messageInfo.getMessage());
        }
    }
}
