package com.learn.rest.webservices.restfulwebservices.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
