package com.learn.rest.webservices.restfulwebservices.dao;

import com.learn.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.learn.rest.webservices.restfulwebservices.modal.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    static List<User> users = new ArrayList<>();
    static int indexing = 1;

    static {
        users.add(new User(indexing++, "sandy", LocalDate.now().minusYears(30)));
        users.add(new User(indexing++, "naveen", LocalDate.now().minusYears(20)));
        users.add(new User(indexing++, "Raj", LocalDate.now().minusYears(10)));
    }

    public List<User> findAllUser() {
        return users;
    }

    public User findUser(int userId) {
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (user == null)
            throw new UserNotFoundException("User not found!");
        return user;
    }

    public User save(User user) {
        user.setId(indexing++);
        users.add(user);
        return user;
    }

    public void deleteById(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
}
