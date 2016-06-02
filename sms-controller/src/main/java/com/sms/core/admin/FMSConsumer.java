package com.sms.core.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ganesan on 28/05/16.
 */
public class FMSConsumer {

    public static class CategoryDocTypeInfo {
        private String type;
        private String uploadCategoryName;
        private String desc;
        private String id;

        public String getType() {
            return type;
        }

        public String getUploadCategoryName() {
            return uploadCategoryName;
        }

        public String getDesc() {
            return desc;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return new ToStringCreator(this)
                    .append("id", id)
                    .append("desc", desc)
                    .append("type", type)
                    .append("uploadCategoryName", uploadCategoryName)
                    .toString();
        }
    }


    public static class UploadInfo {

        private Long documentTypeId;
        private String uploaderId;
        private String fileInfo;
        private String fileName;

        public UploadInfo(Long documentTypeId, String uploaderId, String fileInfo, String fileName) {
            this.documentTypeId = documentTypeId;
            this.uploaderId = uploaderId;
            this.fileInfo = fileInfo;
            this.fileName = fileName;
        }

        public Long getDocumentTypeId() {
            return documentTypeId;
        }

        public String getUploaderId() {
            return uploaderId;
        }

        public String getFileInfo() {
            return fileInfo;
        }

        public String getFileName() {
            return fileName;
        }


    }

    public static class DocumentInfo {
        private Long documentTypeId;
        private String uploaderId;
        private String fileInfo;
        private String fileName;
        private String fileLocation;
        private Long id;

        public Long getDocumentTypeId() {
            return documentTypeId;
        }

        public String getUploaderId() {
            return uploaderId;
        }

        public String getFileInfo() {
            return fileInfo;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFileLocation() {
            return fileLocation;
        }

        public Long getId() {
            return id;
        }
    }


    public static void main(String[] args) throws Exception {

        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
        String url ="http://localhost:8090/fms/upload";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        final String filename = "somefile.txt";
        HttpHeaders headerDocType = new HttpHeaders();
        headerDocType.setContentType(MediaType.APPLICATION_JSON);
        UploadInfo info = new UploadInfo(1L, "TEST1", "testing", filename);
        map.add("docinfo", new HttpEntity<>(info, headerDocType));
        ByteArrayResource contentsAsResource = new ByteArrayResource("this is a testing think".getBytes()){
            @Override
            public String getFilename(){
                return filename;
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        map.add("file", new HttpEntity<>(contentsAsResource, headers));

        HttpEntity<Object> requestEntity = new HttpEntity<>(map);
        ListenableFuture<ResponseEntity<DocumentInfo>> future = asycTemp.exchange(url, HttpMethod.POST, requestEntity, DocumentInfo.class);
        try {
//
//            String result = asycTemp.postForEntity(url, map, String.class);
//            //waits for the result
            ResponseEntity<DocumentInfo> entity = future.get();
            //prints body source code for the given URL
            System.out.println(entity.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
