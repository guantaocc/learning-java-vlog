package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(long id) {
        super("用户不存在，id=" + id);
    }
}
