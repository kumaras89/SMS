package com.sms.core.document;

import com.sms.core.common.Do;
import com.sms.core.common.Promise;
import com.sms.core.common.React;
import com.sms.core.common.Reader;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 07/06/16.
 */
public class DocumentFacade {

    public static Reader<String, Promise<DocInfo>> upload(InputStream file, UploadInfo uploadinfo) {
        return Reader.of(fmsServer -> React.of(new LinkedMultiValueMap<>())
                .thenV(map -> {
                    HttpHeaders headerDocType = new HttpHeaders();
                    headerDocType.setContentType(MediaType.APPLICATION_JSON);
                    map.add("docinfo", new HttpEntity<>(uploadinfo, headerDocType));
                } )
                .thenV(map -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                    map.add("file", new HttpEntity<>(new InputStreamResource(file), headers));
                } )
                .then(HttpEntity::new)
                .thenLF(re -> asycTemp().exchange(fmsServer + "/upload", HttpMethod.POST, re, DocInfo.class))
                .getPromise());

    }

    public static Reader<String, Promise<FileSystemResource>> download( Long theId) {

        return Reader.of(fmsServer -> React.of(theId)
                .thenLF(id -> asycTemp().getForEntity(fmsServer + "/download/" + id, FileSystemResource.class))
                .getPromise());
    }


    public static Reader<String, Promise<List<DocType>>> getAllDocTypes( String category) {

        return Reader.of(fmsServer -> React.of(getResult(fmsServer + "/categorydoctypes", new ParameterizedTypeReference<List<DocType>>() {}))
                        .then(docTypes -> docTypes.stream().filter(docType -> docType.getUploadCategoryName().equals(category) ).collect(Collectors.toList()))
                        .getPromise());
    }

    public static Reader<String, Promise<List<DocInfo>>> getAllDocs(String uploaderid) {
        return Reader.of(fmsServer ->
                React.of(uploaderid)
                        .thenLF(uid -> getResult(fmsServer + "/documents/" + uid,  new ParameterizedTypeReference<List<DocInfo>>() {}))
                        .getPromise()
                );
    }


    private static <T> ListenableFuture<ResponseEntity<T>> getResult(String url, ParameterizedTypeReference<T> responseType) {
        HttpHeaders headerDocType = new HttpHeaders();
        headerDocType.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headerDocType);
        return asycTemp().exchange(url, HttpMethod.GET, requestEntity, responseType);
    }

    private static AsyncRestTemplate asycTemp() {
        return new AsyncRestTemplate();
    }

    public static void main(String[] args) throws Exception {
        String fmsServer = "http://localhost:8091/fms";
        String category = "TEACHER";


        Long docTypeId = getAllDocTypes(category).with(fmsServer).get().get().get(0).getId();
        System.out.println(docTypeId);
        UploadInfo ul = new UploadInfo(docTypeId, "TEST1", "pan card", "pancard.txt");
        InputStream is = new ByteArrayInputStream("test asdf".getBytes());

//        Long fileId = upload(new ByteArrayInputStream("test asdf".getBytes()), ul).with(fmsServer).get().get().getId();
//        System.out.println(fileId);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        map.add("file", new HttpEntity<>(new InputStreamResource(is), headers));

        HttpHeaders headerDocType = new HttpHeaders();
        headerDocType.setContentType(MediaType.APPLICATION_JSON);
        map.add("uploadInfo", new HttpEntity<>(ul, headerDocType));


        HttpEntity<Object> requestEntity = new HttpEntity<>(map);

        System.out.println(requestEntity);

        DocInfo docInfo = asycTemp().exchange(fmsServer + "/upload", HttpMethod.POST, requestEntity, DocInfo.class).get().getBody();

    }
}
