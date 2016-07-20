package com.sms.core;

import com.sms.core.message.SMSLoginDetails;
import com.sms.core.message.SenderDetailsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
@Configuration
public class StudentPortalCoreConfiguration {


    @Value("${SMS_USERNAME}")
    private String user ;
    @Value("${SMS_PASSWORD}")
    private String password ;
    @Value("${SMS_SENDER}")
    private String senderId;
    @Value("${SMS_HASHCODE}")
    private String hash;
    @Value("${api.sms.server}")
    private String smsServer;


    @Autowired
    private List<SenderDetailsProvider> senderDeatilsProviders;


    @Bean(name = "senderDetailsProviders")
    public Map<String, SenderDetailsProvider> senderDeatilsProviderMap() {
        return senderDeatilsProviders
            .stream()
            .collect(Collectors.toMap(SenderDetailsProvider::getType, Function.identity()));
    }

    @Bean(name = "smsLoginDetails")
    public SMSLoginDetails smsLoginDetails() {
        return SMSLoginDetails.builder().on(SMSLoginDetails::getPassword).set(password)
                                        .on(SMSLoginDetails::getHash).set(hash)
                                        .on(SMSLoginDetails::getSender).set(senderId)
                                        .on(SMSLoginDetails::getUsername).set(user)
                                        .on(SMSLoginDetails::getSmsServer).set(smsServer)
                                        .build();

    }
}
