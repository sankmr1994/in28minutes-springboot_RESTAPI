package com.learn.rest.webservices.restfulwebservices.service;

import com.learn.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.learn.rest.webservices.restfulwebservices.modal.Post;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import com.learn.rest.webservices.restfulwebservices.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostDetails(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty())
            throw new PostNotFoundException("Post not found!");
        return post.get();
    }

    public List<Post> getUsersPosts(Integer userId) {
        User user = new User();
        user.setId(userId);
        return postRepository.findAllByUser(user);
    }

    public Post getUserPostDetails(Integer postId, User user) {
        Optional<Post> post = postRepository.findByIdAndUser(postId, user);
        if (post.isEmpty())
            throw new PostNotFoundException("Post not found!");
        return post.get();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }


    @Transactional
    public void deletePostById(User user, Integer postId) {
        postRepository.deleteByIdAndUser(postId, user);
    }
}
