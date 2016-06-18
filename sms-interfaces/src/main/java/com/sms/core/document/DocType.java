package com.sms.core.document;

/**
 * Created by Ganesan on 04/06/16.
 */
public class DocType {

    private String type;
    private String uploadCategoryName;
    private String desc;
    private Boolean multiple;
    private Long id;

    public String getType() {
        return type;
    }

    public String getUploadCategoryName() {
        return uploadCategoryName;
    }

    public String getDesc() {
        return desc;
    }

    public Long getId() {
        return id;
    }

    public Boolean isMultiple() {
        return multiple;
    }
}
