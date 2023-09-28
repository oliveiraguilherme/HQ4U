package com.quadrinhos.catalog.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No Content")
public class ElementoVazioException extends RuntimeException {

    public ElementoVazioException(String message){
        super(message);
    }

    public ElementoVazioException(String message, Throwable cause){
        super(message, cause);
    }
}
