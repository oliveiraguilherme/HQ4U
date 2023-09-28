package com.quadrinhos.catalog.controller.exception;

import com.quadrinhos.catalog.exception.AppException;
import com.quadrinhos.catalog.exception.BadRequestException;
import com.quadrinhos.catalog.repository.exception.NotFoundException;
import com.quadrinhos.catalog.service.exception.ComicsInvalidException;
import com.quadrinhos.catalog.service.exception.ElementoVazioException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExcapetionHandler {
    @ExceptionHandler(ComicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleComicNotFoundException(ComicNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementoVazioException.class)
    public ResponseEntity<ErrorResponse> handleElementoVazioException(ElementoVazioException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ComicsInvalidException.class)
    public ResponseEntity<ErrorResponse> handlerComicsInvalidException(ComicsInvalidException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getDetail());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handlerIllegalArgumentException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse("Invalid request parameter: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handlerAppException(AppException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequestException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @Getter
    @Setter
    private static class ErrorResponse{
        private String message;
        private String detail;

        public ErrorResponse(String message) {
            this.message = message;
        }
        public ErrorResponse(String message, String detail){
            this.message = message;
            this.detail = detail;
        }
    }
}
