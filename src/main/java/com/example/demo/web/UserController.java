package com.example.demo.web;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setId(100L);
        return user;
    }

    @GetMapping("/users/list")
    public List<User> listusers() {
        return Arrays.asList(
                new User(1L, "Tom", 20),
                new User(2L, "Jerry", 22),
                new User(3L, "Alice", 25));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        logger.debug("开始查询用户，id={}", id);

        if (id <= 0) {
            throw new IllegalArgumentException("用户 ID 必须大于 0");
        }

        User user = userService.findById(id);

        logger.info("用户查询完成，id={}", id);
        return user;
    }
}
