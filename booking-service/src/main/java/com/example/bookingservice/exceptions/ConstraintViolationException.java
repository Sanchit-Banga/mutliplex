package com.example.bookingservice.exceptions;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException(String msg){
        super(msg);
    }
}
