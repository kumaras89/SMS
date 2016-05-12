package com.sms.core;

@SuppressWarnings("serial")
public class SmsException extends RuntimeException {

	public SmsException() {
		super();
	}

	public SmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmsException(String message) {
		super(message);
	}

	public SmsException(Throwable cause) {
		super(cause);
	}
}
