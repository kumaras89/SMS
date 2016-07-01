package com.sms.core.student;

import com.sms.core.common.DateUtils;
import com.sms.core.document.DocumentCallBack;
import com.sms.core.document.DocumentFacade;
import com.sms.core.document.UpdateInfo;
import com.sms.core.identity.IdCard;
import com.sms.core.identity.IdCardStatus;
import com.sms.core.repositery.IdCardRepository;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Ganesan on 20/06/16.
 * <p></p>
 */
@Service(value = "STUDENT")
@Transactional
public class StudentDocumentCallBack implements DocumentCallBack {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IdCardRepository idCardRepository;

    @Value("${fms.server}")
    private String fmsServer;

    @Override
    public String getUploaderName(final String uploaderId) {
        final Student student = studentRepository.findByCode(uploaderId);
        return student.getName();
    }

    @Override
    public void updateUploader(final UpdateInfo updateInfo) {
        final Student student = studentRepository.findByCode(updateInfo.getUploaderId());
        studentRepository.updateStatus(updateInfo.getUploaderId(), StudentStatus.valueOf(updateInfo.getStatus()));
        IdCard idCard = IdCard.builder()
                .on(IdCard::getIdentityCode).set(updateInfo.getUploaderId())
                .on(IdCard::getName).set(student.getName())
                .on(IdCard::getAddress).set(student.getBranch().getAddress())
                .on(IdCard::getCreatedDate).set(new Date())
                .on(IdCard::getLastModifiedDate).set(new Date())
                .on(IdCard::getValidUpto).set(DateUtils.addYear(student.getCreatedDate(), 2))
                .on(IdCard::getStatus).set(IdCardStatus.REQUESTED)
                .on(IdCard::getFmsId).set(DocumentFacade.getDocIdOfType(updateInfo.getUploaderId(), updateInfo.getCategory(), "PHOTO").with(fmsServer))
                .build();
        idCardRepository.save(idCard);
    }
}
