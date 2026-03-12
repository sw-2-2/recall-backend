package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {
    private final String email;

    public DuplicateEmailException(String email, String message) {
        super(message);
        this.email = email;
    }
}
