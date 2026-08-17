package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L, "Tom", 20),
            new User(2L, "Jerry", 22),
            new User(3L, "Alice", 25)));

    private long nextId = 4L;

    public synchronized List<User> findAll() {
        return new ArrayList<User>(users);
    }

    public synchronized User create(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public synchronized User update(long id, User newUser) {
        for (User user : users) {
            if (user.getId().longValue() == id) {
                user.setUsername(newUser.getUsername());
                user.setAge(newUser.getAge());

                return user;
            }
        }

        throw new UserNotFoundException(id);
    }

    

    public synchronized void deleteById(long id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId().longValue() == id) {
                iterator.remove();
                return;
            }
        }

        throw new UserNotFoundException(id);
    }

    public synchronized User findById(long id) {
        for (User user : users) {
            if (user.getId().longValue() == id) {
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }
}
