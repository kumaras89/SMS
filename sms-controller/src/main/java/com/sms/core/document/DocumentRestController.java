package com.sms.core.document;

import com.sms.core.DeferredResultProvider;
import com.sms.core.common.FunctionUtils;
import com.sms.core.common.Promise;
import com.sms.core.common.React;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ganesan on 04/06/16.
 */
@RestController
@RequestMapping(value = "/document")
public class DocumentRestController {

    @Value("${fms.server}")
    private String fmsServer;


    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
    public DeferredResult<ResponseEntity<DocInfo>> upload(@RequestParam("file") MultipartFile file, @RequestPart("uploadinfo") UploadInfo uploadinfo) throws IOException {


        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();


        HttpHeaders headerDocType = new HttpHeaders();
        headerDocType.setContentType(MediaType.APPLICATION_JSON);
        map.add("docinfo", new HttpEntity<>(uploadinfo, headerDocType));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        map.add("file", new HttpEntity<>(new InputStreamResource(file.getInputStream()), headers));

        HttpEntity<Object> requestEntity = new HttpEntity<>(map);
        HttpMethod method = HttpMethod.POST;

        ListenableFuture<ResponseEntity<DocInfo>> future = asycTemp().exchange(fmsServer + "/upload", method, requestEntity, DocInfo.class);
        return DeferredResultProvider.createDeferredResult(getAsPromise(future), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<FileSystemResource>> download(@PathVariable Long id) {
        ListenableFuture<ResponseEntity<FileSystemResource>> future = asycTemp().getForEntity(fmsServer + "/download/" + id, FileSystemResource.class);
        return DeferredResultProvider.createDeferredResult(getAsPromise(future), HttpStatus.OK);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public DeferredResult<ResponseEntity<Void>> update(@RequestParam String category, @RequestParam String status) {
        return DeferredResultProvider.createDeferredResult(null, HttpStatus.OK);
    }


    @RequestMapping("/doctypes/{category}")
    public DeferredResult<ResponseEntity<List<DocType>>> getAllDocTypes(@PathVariable String category) {

        Promise<List<DocType>> promise = React.of(getResult(fmsServer + "/categories", new ParameterizedTypeReference<List<Category>>() {
        }))
                .thenCF(FunctionUtils::buildCompletableFutureFromListenableFuture)
                .then(r -> r.getBody())
                .then(categoryList -> categoryList.stream().filter(cat -> category.equals(cat.getName())).findFirst().get())
                .then(cat -> getResult(fmsServer + "/categorydoctypes/" + cat.getId(), new ParameterizedTypeReference<List<DocType>>() {
                }))
                .thenCF(FunctionUtils::buildCompletableFutureFromListenableFuture)
                .then(r -> r.getBody())
                .getPromise();


        return DeferredResultProvider.createDeferredResult(promise, HttpStatus.OK);
    }

    @RequestMapping("/documents/{uploaderid}")
    public DeferredResult<ResponseEntity<List<DocInfo>>> getAllDocs(@PathVariable String uploaderid) {
        ParameterizedTypeReference<List<DocInfo>> responseType = new ParameterizedTypeReference<List<DocInfo>>() {
        };
        ListenableFuture<ResponseEntity<List<DocInfo>>> future = getResult(fmsServer + "/documents/" + uploaderid,  responseType);
        return DeferredResultProvider.createDeferredResult(getAsPromise(future), HttpStatus.OK);
    }


    private static <T> Promise<T> getAsPromise(ListenableFuture<ResponseEntity<T>> listenableFuture) {
        return React.of(listenableFuture)
                .thenCF(FunctionUtils::buildCompletableFutureFromListenableFuture)
                .then(r -> r.getBody())
                .getPromise();
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


}
