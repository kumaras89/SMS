package com.sms.core.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.core.DeferredResultProvider;
import com.sms.core.common.React;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/document")
public class DocumentRestController {

    @Value("${fms.server}")
    private String fmsServer;

    @Autowired
    private Map<String, DocumentCallBack> documentCallBacks;


    @RequestMapping(value = "/upload",
        method = RequestMethod.POST,
        consumes = {"multipart/form-data"},
        produces = {"application/json"})
    public DeferredResult<ResponseEntity<DocInfo>> upload(
        @RequestParam("uploadinfo") final String uploadinfo,
        @RequestParam("file") final MultipartFile file) throws IOException {

        final UploadInfo uploadInfoObj = new ObjectMapper().readValue(uploadinfo, UploadInfo.class);
        return DeferredResultProvider.createDeferredResult(DocumentFacade.upload(file.getBytes(), uploadInfoObj)
            .with(fmsServer), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/download/{id}/{filename}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<InputStreamResource>> download(
        @PathVariable final Long id,
        @PathVariable final String filename) throws ExecutionException, InterruptedException {

        return DeferredResultProvider.createDeferredResultRE(DocumentFacade.download(id).with(fmsServer));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<Long>> delete(@PathVariable final Long id) {
        return DeferredResultProvider.createDeferredResult(DocumentFacade.delete(id).with(fmsServer), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadername/{category}/{uploaderid}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<Map<String, String>>> getUploaderName(
        @PathVariable final String category,
        @PathVariable final String uploaderid) {

        return DeferredResultProvider.createDeferredResult(React.of(category).then(documentCallBacks::get)
            .then(dcb -> dcb.getUploaderName(uploaderid))
            .then(name -> {
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                return map;
            }).getPromise(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public DeferredResult<ResponseEntity<Void>> update(@RequestBody final UpdateInfo updateInfo) {
        return DeferredResultProvider
            .createDeferredResult(
                React.of(updateInfo.getCategory())
                    .then(documentCallBacks::get)
                    .thenVoid(dcb -> dcb.updateUploader(updateInfo))
                    .getPromise(), HttpStatus.OK);
    }


    @RequestMapping(value = "/doctypes/{category}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<DocType>>> getAllDocTypes(@PathVariable final String category) {
        return DeferredResultProvider.createDeferredResult(
            DocumentFacade.getAllDocTypes(category).with(fmsServer),
            HttpStatus.OK);
    }

    @RequestMapping(value = "/documents/{uploaderid}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<DocInfo>>> getAllDocs(@PathVariable final String uploaderid) {
        return DeferredResultProvider.createDeferredResult(
            DocumentFacade.getAllDocs(uploaderid).with(fmsServer),
            HttpStatus.OK);
    }


}
