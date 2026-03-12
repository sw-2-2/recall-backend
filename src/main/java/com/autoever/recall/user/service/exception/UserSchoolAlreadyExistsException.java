package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class UserSchoolAlreadyExistsException extends RuntimeException {
    private final String type;
    private static final String MESSAGE = "이미 해당 유형의 학교와 연결되어 있습니다";

    public UserSchoolAlreadyExistsException(String type) {
        super(MESSAGE);
        this.type = type;
    }
}
