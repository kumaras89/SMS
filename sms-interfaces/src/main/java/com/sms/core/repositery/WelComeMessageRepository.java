package com.sms.core.repositery;

import com.sms.core.message.WelcomeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sathish on 8/12/2016.
 */
public interface WelComeMessageRepository extends JpaRepository<WelcomeMessage,String>
{
    WelcomeMessage findByCode(String code);
}
