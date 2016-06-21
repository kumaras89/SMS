package com.sms.core.student;

import com.sms.core.document.DocumentCallBack;
import com.sms.core.document.UpdateInfo;
import com.sms.core.repositery.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Ganesan on 20/06/16.
 */
@Service
@Qualifier(value = "STUDENT")
public class StudentDocumentCallBack implements DocumentCallBack {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String getUploaderName(String uploaderId) {
        Student student = studentRepository.findByCode(uploaderId);
        return student.getName();
    }

    @Override
    public void updateUploader(UpdateInfo updateInfo) {
        studentRepository.updateStatus(updateInfo.getUploaderId(), updateInfo.getStatus());
    }
}
