package com.learn.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/personName")
    public Personv1 personNameV1UsingPath() {
        return new Personv1("sandy kmr");
    }

    @GetMapping("/v2/personName")
    public Personv2 personNameV2UsingPath() {
        return new Personv2(new Name("sandy", "kmr"));
    }

    @GetMapping(value = "/personNameUsingParam", params = "Version=1")
    public Personv1 personNameV1UsingRequestParam() {
        return new Personv1("sandy kmr");
    }

    @GetMapping(value = "/personNameUsingParam", params = "Version=2")
    public Personv2 personNameV2UsingRequestParam() {
        return new Personv2(new Name("sandy", "kmr"));
    }

    @GetMapping(value = "/personNameUsingHeader", headers = "X-API-VERSION=1")
    public Personv1 personNameV1UsingHeader() {
        return new Personv1("sandy kmr");
    }

    @GetMapping(value = "/personNameUsingHeader", headers = "X-API-VERSION=2")
    public Personv2 personNameV2UsingHeader() {
        return new Personv2(new Name("sandy", "kmr"));
    }
}
