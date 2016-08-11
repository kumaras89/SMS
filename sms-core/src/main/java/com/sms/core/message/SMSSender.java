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
    public static Function<SMSConfig, String> sendSms(final SMSDetails senderDetails) {
        return messageConfig -> Do.of(senderDetails)
            .then(sd -> {
                final MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
                uriVariables.add("username", messageConfig.getSmsLoginDetails().getUsername());
                uriVariables.add("hash",messageConfig.getSmsLoginDetails().getHash());
                uriVariables.add("message", encodeMessage(senderDetails.getMessage()));
                uriVariables.add("sender", messageConfig.getSmsLoginDetails().getSender());
                uriVariables.add("numbers", senderDetails.getPhoneNumber());
                uriVariables.add("unicode", "1");
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
    public static String encodeMessage(final String message) {
        StringBuilder newMessage = new StringBuilder();
        newMessage.append("@U");
        for (char c : message.toCharArray()) {
            String charHex = String.format("%1$4s", Integer.toHexString(c));
            newMessage.append(charHex);
        }
        return newMessage.toString();
    }

    /**
     *
     * @return
     */
    private static RestTemplate getAsyncRestTemplate() {
        return new RestTemplate();
    }
}
