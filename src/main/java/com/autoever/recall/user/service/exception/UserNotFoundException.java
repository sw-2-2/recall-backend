package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String email;
    private static final String MESSAGE = "요청한 회원을 찾을 수 없습니다";

    public UserNotFoundException(String email) {
        super(MESSAGE);
        this.email = email;
    }
}
