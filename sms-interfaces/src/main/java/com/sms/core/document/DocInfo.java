package com.sms.core.document;


/**
 * Created by Ganesan on 04/06/16.
 */
public class DocInfo {

    private Long documentTypeId;
    private Long fileSequence;
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

    public Long getFileSequence() {
        return fileSequence;
    }
}
