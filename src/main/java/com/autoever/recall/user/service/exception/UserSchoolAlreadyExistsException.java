package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class UserSchoolAlreadyExistsException extends RuntimeException {
    private final String type;

    public UserSchoolAlreadyExistsException(String type, String message) {
        super(message);
        this.type = type;
    }
}
