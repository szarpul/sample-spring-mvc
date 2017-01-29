package com.intive.samples.spring.mvc.exceptions;

//@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class HelloException extends Exception {

    public HelloException(String message) {
        super(message);
    }
}
