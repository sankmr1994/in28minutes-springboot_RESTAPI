package com.learn.rest.webservices.restfulwebservices.repository;

import com.learn.rest.webservices.restfulwebservices.modal.Post;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUser(User user);

    Optional<Post> findByIdAndUser(Integer postId, User user);

    void deleteByIdAndUser(Integer postId, User user);
}
