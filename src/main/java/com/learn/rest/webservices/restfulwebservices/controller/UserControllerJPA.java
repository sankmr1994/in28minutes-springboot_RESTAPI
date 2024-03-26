package com.learn.rest.webservices.restfulwebservices.controller;

import com.learn.rest.webservices.restfulwebservices.modal.User;
import com.learn.rest.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserControllerJPA {

    private UserService userService;

    public UserControllerJPA(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/JPA/users")
    public List<User> getAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/JPA/users/{id}")
    public EntityModel<User> getUser(@PathVariable("id") int userId) {
        User user = userService.findUser(userId);

        EntityModel<User> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        userEntityModel.add(linkBuilder.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("/JPA/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        //Get Current location path
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/JPA/users/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        userService.deleteById(userId);
    }

}
