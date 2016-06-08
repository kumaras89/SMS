package com.sms.core.document;

import com.sms.core.DeferredResultProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<DocInfo> upload(@RequestParam("file") MultipartFile file, @RequestPart("uploadinfo") UploadInfo uploadinfo) throws IOException {
         return DeferredResultProvider.createDeferredResult(DocumentFacade.upload(file.getInputStream(), uploadinfo).with(fmsServer), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> download(@PathVariable Long id) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.download(id).with(fmsServer), HttpStatus.OK);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestParam String category, @RequestParam String status) {
        return DeferredResultProvider.createDeferredResult(null, HttpStatus.OK);
    }


    @RequestMapping("/doctypes/{category}")
    public ResponseEntity<List<DocType>> getAllDocTypes(@PathVariable String category) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.getAllDocTypes(category).with(fmsServer), HttpStatus.OK);
    }

    @RequestMapping("/documents/{uploaderid}")
    public ResponseEntity<List<DocInfo>> getAllDocs(@PathVariable String uploaderid) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.getAllDocs(uploaderid).with(fmsServer), HttpStatus.OK);
    }


}
