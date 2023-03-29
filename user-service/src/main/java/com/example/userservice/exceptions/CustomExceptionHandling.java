package com.example.userservice.exceptions;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleExceptions(NotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(webRequest);
        response.setMessage(exception.getMessage());
        response.setStatus("404");
        response.setError("Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleExceptions(BadRequestException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(webRequest);
        response.setMessage(exception.getMessage());
        response.setStatus("400");
        response.setError("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleExceptions(AuthException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(webRequest);
        response.setMessage(exception.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value() + "");
        response.setError("Unauthorized");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleExceptions(ConstraintViolationException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(webRequest);
        response.setMessage("JSON body is not matching with the schema");
        response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value() + "");
        response.setError("Unsupported Media Type");
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
