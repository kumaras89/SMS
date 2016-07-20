package com.sms.core.message;


import com.sms.core.common.Do;
import javaslang.Tuple;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
                final MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
                uriVariables.add("username", messageConfig.getSmsLoginDetails().getUsername());
                uriVariables.add("hash",messageConfig.getSmsLoginDetails().getHash());
                uriVariables.add("message", senderDetails.getMessage());
                uriVariables.add("sender", messageConfig.getSmsLoginDetails().getSender());
                uriVariables.add("numbers", senderDetails.getPhoneNumber());
                final URI uri = UriComponentsBuilder
                                                  .fromHttpUrl(messageConfig.getSmsLoginDetails().getSmsServer())
                                                  .queryParams(uriVariables).build().toUri();
                final MultiValueMap<String,String> headers = new HttpHeaders();
                headers.add("Content-Length",Integer.toString(senderDetails.getMessage().length()));
                final HttpEntity<Object> request =  new HttpEntity<>(headers);
                return Tuple.of(uri,request);
            })
            .then(tuple ->  getAsyncRestTemplate().exchange(tuple._1,HttpMethod.POST,tuple._2,String.class))
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
