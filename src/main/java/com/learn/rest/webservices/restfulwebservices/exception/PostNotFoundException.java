package com.learn.rest.webservices.restfulwebservices.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String err) {
        super(err);
    }
}
