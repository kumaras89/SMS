package com.sms.core.message;


import com.sms.core.common.Do;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

public class SMSSender {

    /**
     * @param senderDetails
     * @return
     */
    public static Function<MessageConfig, String> sendSms(final SMSDetails senderDetails) {
        return messageConfig -> Do.of(senderDetails)
            .then(sd -> {
                final Map<String, String> uriVariables = new WeakHashMap<>();
                uriVariables.put("pass", messageConfig.getSmsLoginDetails().getPassword());
                uriVariables.put("sender", messageConfig.getSmsLoginDetails().getSenderId());
                uriVariables.put("user", messageConfig.getSmsLoginDetails().getUser());
                uriVariables.put("text", senderDetails.getMessage());
                uriVariables.put("phone", senderDetails.getPhoneNumber());
                uriVariables.put("priority", messageConfig.getSmsLoginDetails().getPriority());
                uriVariables.put("stype",messageConfig.getSmsLoginDetails().getSmstype());
                final MultiValueMap<String,String> headers = new HttpHeaders();
                headers.add("","");
                headers.add("","");
                return new HttpEntity<>(uriVariables,headers);
            })
            .then(httpEntity -> getAsyncRestTemplate().exchange(
                messageConfig.getSmsLoginDetails().getSmsServer(),
                HttpMethod.POST,
                httpEntity,
                String.class))
            .then(response -> response.getBody())
            .get();
    }

    /**
     * @return
     */
    private static RestTemplate getAsyncRestTemplate() {
        return new RestTemplate();
    }
}
