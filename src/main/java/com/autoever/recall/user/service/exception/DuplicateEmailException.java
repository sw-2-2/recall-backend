package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {
    private final String email;
    private static final String MESSAGE = "같은 이메일의 회원이 존재합니다";

    public DuplicateEmailException(String email) {
        super(MESSAGE);
        this.email = email;
    }
}
