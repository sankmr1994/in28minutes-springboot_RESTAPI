package com.learn.rest.webservices.restfulwebservices.controller;

import com.learn.rest.webservices.restfulwebservices.modal.Post;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import com.learn.rest.webservices.restfulwebservices.service.PostService;
import com.learn.rest.webservices.restfulwebservices.service.UserService;
import javafx.geometry.Pos;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    private UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public EntityModel<Post> getPostDetails(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostDetails(postId);
        EntityModel<Post> postEntityModel = EntityModel.of(post);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPosts());
        postEntityModel.add(linkBuilder.withRel("all-posts"));
        return postEntityModel;
    }


    @GetMapping("/users/{userId}/posts")
    public List<Post> getUsersPosts(@PathVariable("userId") Integer userId) {
        return postService.getUsersPosts(userId);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public EntityModel<Post> getUserPostDetails(@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {
        User user = userService.findUser(userId);
        Post post = postService.getUserPostDetails(postId, user);
        EntityModel<Post> postEntityModel = EntityModel.of(post);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsersPosts(userId));
        postEntityModel.add(linkBuilder.withRel("all-posts"));
        return postEntityModel;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId") Integer userId) {
        User user = userService.findUser(userId);
        post.setUser(user);
        Post savedPost = postService.createPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public void deletePostById(@PathVariable("userId") Integer userId,@PathVariable("postId") Integer postId) {
        User user = userService.findUser(userId);
        postService.deletePostById(user,postId);
    }

}
