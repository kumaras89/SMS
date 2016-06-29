package com.sms.core.identity;

public class IdCardSearchCriteria {

    private String uploaderId;
    private String uploaderCategory;
    private IdCardStatus status;
    private Integer year;

    public String getUploaderId() {
        return uploaderId;
    }

    public String getUploaderCategory() {
        return uploaderCategory;
    }

    public IdCardStatus getStatus() {
        return status;
    }

    public Integer getYear() {
        return year;
    }
}