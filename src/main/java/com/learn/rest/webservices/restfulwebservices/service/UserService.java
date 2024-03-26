package com.learn.rest.webservices.restfulwebservices.service;

import com.learn.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import com.learn.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("User not found!");
        return user.get();
    }

    public User save(User user) {
        user = userRepository.save(user);
        return user;
    }

    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
