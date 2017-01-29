package com.intive.samples.spring.mvc.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return "Some problems with arguments: " + ex.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public String handleIllegalStateException(IllegalStateException ex) {
        return "Some problems with state: " + ex.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String handleIllegalStateException(NullPointerException ex) {
        return ex.getClass().getSimpleName() + " has occurred.";
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return new ResponseEntity<Object>("There is something wrong with request. " + ex.getMessage(), headers, status);

    }
}
