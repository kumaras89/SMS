package com.sms.core.identity;

public class IdCardSearchCriteria {

    private String identityCode;
    private String name;
    private IdCardStatus status;
    private Integer year;

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