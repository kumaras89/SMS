package com.sms.core.repositery;

import com.sms.core.message.Message;
import com.sms.core.student.StudentScholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by sathish on 7/12/2016.
 */
public interface MessageRepository extends JpaRepository<Message,String>
{

}
