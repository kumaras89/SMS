package com.sms.core.identity;

public class IdCardSearchCriteria {

    private String identityCode;
    private String name;
    private IdCardStatus status;
    private Integer year;
    private String branch;

    public String getBranch() {
        return branch;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public String getName() {
        return name;
    }

    public IdCardStatus getStatus() {
        return status;
    }

    public Integer getYear() {
        return year;
    }
}