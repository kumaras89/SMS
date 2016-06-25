package com.sms.core;

import com.sms.core.common.ErrorInfo;

@SuppressWarnings("serial")
public class SmsException extends RuntimeException {

	private ErrorInfo errorInfo;

	public SmsException(String message, Throwable cause) {
		super(message, cause);
		errorInfo = new ErrorInfo(null, message);
	}

	public SmsException(String fieldName, String message) {
		super(message);
		errorInfo = new ErrorInfo(fieldName , message);
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
}
