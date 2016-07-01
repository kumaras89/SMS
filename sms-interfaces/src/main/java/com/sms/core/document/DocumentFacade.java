package com.sms.core.document;

import com.sms.core.common.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 07/06/16.
 */
public class DocumentFacade {

    public static Reader<String, Promise<DocInfo>> upload(byte[] file, UploadInfo uploadinfo) {
        return Reader.of(fmsServer -> React.of(uploadinfo)
                .then(upInfo -> {
                    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
                    HttpHeaders headerDocType = new HttpHeaders();
                    headerDocType.setContentType(MediaType.APPLICATION_JSON);
                    map.add("uploadInfo", new HttpEntity<>(upInfo, headerDocType));
                    ByteArrayResource contentsAsResource = new ByteArrayResource(file){
                        @Override
                        public String getFilename(){
                            return uploadinfo.getFileName();
                        }
                    };
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                    map.add("file", new HttpEntity<>(contentsAsResource, headers));
                    HttpEntity<Object> requestEntity = new HttpEntity<>(map);
                    return requestEntity;
                })
                .thenLF(re -> asycTemp().exchange(fmsServer + "/upload", HttpMethod.POST, re, DocInfo.class))
                .getPromise());

    }

    public static Reader<String, Promise<ResponseEntity<InputStreamResource>>> download(Long theId) {

        return Reader.of(fmsServer -> React.of(theId)
                .thenLF(id -> asycTemp().getForEntity(fmsServer + "/download/" + id, Resource.class))
                .then(fsr -> {
                    try{
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                        headers.add("Pragma", "no-cache");
                        headers.add("Expires", "0");
                        return ResponseEntity
                                .ok()
                                .headers(headers)
                                .contentLength(fsr.contentLength())
                                .contentType(MediaType.parseMediaType("application/octet-stream"))
                                .body(new InputStreamResource(fsr.getInputStream()));
                    } catch(IOException e) {
                        throw new IllegalStateException(e);
                    }

                })
                .getPromise());
    }

    public static Reader<String, Promise<Long>> delete( Long theId) {

        return Reader.of(fmsServer -> React.of(theId)
                .thenLF(id -> asycTemp().exchange(fmsServer + "/delete/" + id, HttpMethod.DELETE, getEmptyRequestEntity(), Long.class))
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
        return asycTemp().exchange(url, HttpMethod.GET, getEmptyRequestEntity(), responseType);
    }

    private static HttpEntity<Object> getEmptyRequestEntity() {
        HttpHeaders headerDocType = new HttpHeaders();
        headerDocType.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headerDocType);
        return requestEntity;
    }

    public static Reader<String, Long> getDocIdOfType(String uploaderId, String category, String docType) {
        return Reader.of(fmsServer ->
                        React.of(category)
                                .thenP(s -> getAllDocTypes(s).with(fmsServer))
                                .then(docTypes -> docTypes.stream().filter(docType1 -> docType1.getType().equals(docType)).findFirst().get())
                                .thenR(docType1 -> React.of(getAllDocs(uploaderId).with(fmsServer))
                                        .then(docs -> docs.stream().filter(doc -> doc.getDocumentTypeId().equals(docType1.getId())).findFirst().get()))
                                .then(doc -> doc.getId()).get().join());

    }

    private static AsyncRestTemplate asycTemp() {
        return new AsyncRestTemplate();
    }

}
