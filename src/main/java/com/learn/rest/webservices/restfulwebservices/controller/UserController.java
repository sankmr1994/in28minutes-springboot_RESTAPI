package com.learn.rest.webservices.restfulwebservices.controller;

import com.learn.rest.webservices.restfulwebservices.dao.UserDao;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userDao.findAllUser();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable("id") int userId) {
        User user = userDao.findUser(userId);

        EntityModel<User> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        userEntityModel.add(linkBuilder.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        //Get Current location path
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        userDao.deleteById(userId);
    }

}
