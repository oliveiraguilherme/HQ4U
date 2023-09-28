package com.quadrinhos.catalog.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class ComicNotFoundException extends RuntimeException{
    public ComicNotFoundException(String message){
        super(message);
    }

    public ComicNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
