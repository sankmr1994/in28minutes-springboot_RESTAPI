package com.learn.rest.webservices.restfulwebservices.versioning;

public class Personv2 {

    private Name name;

    public Personv2() {
    }

    public Personv2(Name personName) {
        this.name = personName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
