package com.example.bookingservice.exceptions;

public class AlreadyPresentException extends RuntimeException{
    public AlreadyPresentException(String msg){
        super(msg);
    }
}
