package com.learn.rest.webservices.restfulwebservices.versioning;


public class Personv1 {
    String name;

    public Personv1() {
    }

    public Personv1(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
