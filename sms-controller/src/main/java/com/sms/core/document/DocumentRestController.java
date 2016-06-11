package com.sms.core.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.core.DeferredResultProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ganesan on 04/06/16.
 */
@RestController
@RequestMapping(value = "/document")
public class DocumentRestController {

    @Value("${fms.server}")
    private String fmsServer;


    @RequestMapping(value = "/upload", method = RequestMethod.POST,  consumes = {"multipart/form-data"}, produces = { "application/json" })
    public ResponseEntity<DocInfo> upload(@RequestParam("uploadinfo") String uploadinfo, @RequestParam("file") MultipartFile file) throws IOException {
        UploadInfo uploadInfoObj = new ObjectMapper().readValue(uploadinfo, UploadInfo.class);
        return DeferredResultProvider.createDeferredResult(DocumentFacade.upload(file.getBytes(), uploadInfoObj).with(fmsServer), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/download/{id}/{filename}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable Long id, @PathVariable String filename) throws ExecutionException, InterruptedException {
        return DocumentFacade.download(id).with(fmsServer).get().get();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.delete(id).with(fmsServer), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadername/{uploaderid}", method = RequestMethod.GET)
    public ResponseEntity<String> getUploaderName(@PathVariable String uploaderid) {
        return DeferredResultProvider.createDeferredResult(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestParam UpdateInfo updateInfo) {
        return DeferredResultProvider.createDeferredResult(null, HttpStatus.OK);
    }


    @RequestMapping(value ="/doctypes/{category}", method = RequestMethod.GET)
    public ResponseEntity<List<DocType>> getAllDocTypes(@PathVariable String category) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.getAllDocTypes(category).with(fmsServer), HttpStatus.OK);
    }

    @RequestMapping(value = "/documents/{uploaderid}", method = RequestMethod.GET)
    public ResponseEntity<List<DocInfo>> getAllDocs(@PathVariable String uploaderid) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.getAllDocs(uploaderid).with(fmsServer), HttpStatus.OK);
    }


}
