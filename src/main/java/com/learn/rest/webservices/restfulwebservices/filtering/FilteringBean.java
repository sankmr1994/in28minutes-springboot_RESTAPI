package com.learn.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("beanFilter")
public class FilteringBean {


    private int field1;

    //    @JsonIgnore
    private int field2;

    private int field3;

    public int getField1() {
        return field1;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public int getField3() {
        return field3;
    }

    public void setField3(int field3) {
        this.field3 = field3;
    }
}
