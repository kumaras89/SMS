package com.sms.core.common;

public enum ErrorCode {

    FILE_WRTING_FAILED("FMS_0002");

    ErrorCode(final String status) {
        this.status = status;

    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
