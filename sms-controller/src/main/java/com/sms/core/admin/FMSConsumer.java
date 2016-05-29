package com.sms.core.admin;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ganesan on 28/05/16.
 */
public class FMSConsumer {

    public static class UplocadCategory{
        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        @Override
        public String toString() {
            return "UplocadCategory{" +
                    "name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
        String url ="http://localhost:8090/fms/categories";
        HttpMethod method = HttpMethod.GET;
        ParameterizedTypeReference<List<UplocadCategory>> responseType = new ParameterizedTypeReference<List<UplocadCategory>>() {
        };
        //create request entity using HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<List<UplocadCategory>> requestEntity = new HttpEntity<>(headers);
        ListenableFuture<ResponseEntity<List<UplocadCategory>>> future = asycTemp.exchange(url, method, requestEntity, responseType);
        try {
            //waits for the result
            ResponseEntity<List<UplocadCategory>> entity = future.get();
            //prints body source code for the given URL
            System.out.println(entity.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
