package com.sms.core.document;

/**
 * Created by Ganesan on 04/06/16.
 */
public class UploadInfo {

    private Long documentTypeId;
    private String uploaderId;
    private String fileInfo;
    private String fileName;

    public UploadInfo(){

    }


    public UploadInfo(Long documentTypeId, String uploaderId, String fileInfo, String fileName) {
        this.documentTypeId = documentTypeId;
        this.uploaderId = uploaderId;
        this.fileInfo = fileInfo;
        this.fileName = fileName;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void setFileName(String fileName) {
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
