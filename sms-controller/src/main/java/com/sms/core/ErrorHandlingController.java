package com.sms.core;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sms.core.SmsException;

@ControllerAdvice
public class ErrorHandlingController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleClientException(final MethodArgumentNotValidException e) {

		final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		final StringBuilder errorBuilder = new StringBuilder(); 
		fieldErrors.forEach( fieldError ->  errorBuilder.append(String.format("\"%s\" : \"%s\"", fieldError.getField(), fieldError.getDefaultMessage())).append(","));
		final String errorMessage = errorBuilder.toString(); 
		return String.format("{ \"%s\" : { %s } }", "error", errorMessage.substring(0, errorMessage.lastIndexOf(",")));
	}
	
	@ExceptionHandler(SmsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleServerException(final SmsException e) {

		return String.format("{ \"%s\" : \"%s\" }", "error", e.getMessage());
	}
}