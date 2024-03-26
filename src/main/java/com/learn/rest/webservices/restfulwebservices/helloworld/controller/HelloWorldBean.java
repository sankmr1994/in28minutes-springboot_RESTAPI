package com.learn.rest.webservices.restfulwebservices.helloworld.controller;

public class HelloWorldBean {

    private String msg;

    public HelloWorldBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
