package com.quadrinhos.catalog.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Comic is Invalid!")
public class ComicsInvalidException extends RuntimeException{
    private String message;
    private String detail;
    public ComicsInvalidException(String message){
        super(message);
    }

    public ComicsInvalidException(String message, Throwable cause){
        super(message, cause);
    }

    public ComicsInvalidException(String message, String detail){
        this.message = message;
        this.detail = detail;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
