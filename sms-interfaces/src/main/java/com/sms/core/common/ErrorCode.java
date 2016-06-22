package com.sms.core.common;

import javax.validation.Payload;

public enum ErrorCode implements Payload {

    FILE_WRTING_FAILED("FMS_0002");


    ErrorCode(final String status) {
        this.status = status;

    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
