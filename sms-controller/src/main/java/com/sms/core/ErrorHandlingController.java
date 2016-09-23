package com.sms.core;

import com.sms.core.common.Error;
import com.sms.core.common.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handleClientException(final MethodArgumentNotValidException e) {
        final Error error = new Error();
        error.setErrorInfo(
            e.getBindingResult().getFieldErrors()
                .stream()
                .map(cv -> this.getErrorInfo(cv.getField(), cv.getDefaultMessage())).collect(Collectors.toList()));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SmsException.class)
    public Error handleServerException(final SmsException e) {
        final Error error = new Error();
        error.setErrorInfo(e.getErrorInfo());
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleConstraintViolationException(final ConstraintViolationException e) {
        final Error error = new Error();
        error.setErrorInfo(
            e.getConstraintViolations()
                .stream()
                .map(cv -> this.getErrorInfo(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList()));
        return error;

    }


    @ExceptionHandler(PersistenceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlePersistenceException(final PersistenceException e) {
        Error error = new Error();
        if (e.getCause() instanceof ConstraintViolationException) {
            error = handleConstraintViolationException((ConstraintViolationException) e.getCause());
        } else {
            error.setErrorInfo(getErrorInfo("all", e.getMessage()));
        }
        return error;

    }

    private ErrorInfo getErrorInfo(final String fieldName, final String errorMsg) {
        return ErrorInfo.builder()
            .with(ErrorInfo::getFieldName, fieldName)
            .with(ErrorInfo::getMessage, errorMsg)
            .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String exception(final AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    }

}