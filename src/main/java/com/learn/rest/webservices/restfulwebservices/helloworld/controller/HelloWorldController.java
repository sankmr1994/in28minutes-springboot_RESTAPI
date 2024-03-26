package com.learn.rest.webservices.restfulwebservices.helloworld.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/helloWorld")
    public String sayHelloWorld() {
        return "Hello-World";
    }

    @GetMapping("/helloWorldBean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world by Bean...");
    }

    @GetMapping("/helloByName/{name}")
    public HelloWorldBean helloByName(@PathVariable("name") String userName) {
        return new HelloWorldBean(String.format("Hello World By %s", userName));
    }

    @GetMapping("/goodMorning")
    public String sayGoodMorning() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
