package com.Projekat.exception;

public class ReviewAlreadyExistsException extends RuntimeException{

    public ReviewAlreadyExistsException(String message) {
        super(message);
    }
}
