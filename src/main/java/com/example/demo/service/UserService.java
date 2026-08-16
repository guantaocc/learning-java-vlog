package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@Service
public class UserService {
    public User findById(long id) {
        List<User> users = Arrays.asList(
                new User(1L, "Tom", 20),
                new User(2L, "Jerry", 22),
                new User(3L, "Alice", 25));
        ;
        for (User user : users) {
            if (user.getId().longValue() == id) {
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }
}
