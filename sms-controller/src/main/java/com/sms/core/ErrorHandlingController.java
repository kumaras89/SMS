package com.sms.core;

import com.sms.core.common.Error;
import com.sms.core.common.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Error handleClientException(final MethodArgumentNotValidException e) {
        final Error error = new Error();
        error.setErrorInfo(
                e.getBindingResult().getFieldErrors()
                        .stream()
                        .map(cv -> this.getErrorInfo(cv.getField(), cv.getDefaultMessage())).collect(Collectors.toList()));
        return error;
    }

    @ExceptionHandler(SmsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    String handleServerException(final SmsException e) {

        return String.format("{ \"%s\" : \"%s\" }", "error", e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleConstraintViolationException(ConstraintViolationException e) {
        final Error error = new Error();
        error.setErrorInfo(
                e.getConstraintViolations()
                        .stream()
                        .map(cv -> this.getErrorInfo(cv.getPropertyPath().toString(), cv.getMessage())).collect(Collectors.toList()));
        return error;

    }


    private ErrorInfo getErrorInfo(final String fieldName, final String erroMsg) {
        return ErrorInfo.builder().with(ErrorInfo::getFieldName, fieldName).with(ErrorInfo::getMessage, erroMsg).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String exception(AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    }
}