package com.learn.rest.webservices.restfulwebservices.repository;

import com.learn.rest.webservices.restfulwebservices.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
