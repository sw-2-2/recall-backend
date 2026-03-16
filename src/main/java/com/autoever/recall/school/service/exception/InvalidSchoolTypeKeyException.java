package com.autoever.recall.school.service.exception;

import lombok.Getter;

@Getter
public class InvalidSchoolTypeKeyException extends RuntimeException {
    private final String key;
    private static final String MESSAGE = "잘못된 학교 타입입니다";

    public InvalidSchoolTypeKeyException(String key) {
        super(MESSAGE);
        this.key = key;
    }
}
