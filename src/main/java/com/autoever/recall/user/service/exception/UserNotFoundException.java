package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String email;

    public UserNotFoundException(String email, String message) {
        super(message);
        this.email = email;
    }
}
