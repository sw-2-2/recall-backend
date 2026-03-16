package com.autoever.recall.user.service.exception;

import lombok.Getter;

@Getter
public class SchoolTypeMismatchException extends RuntimeException {
    private final String type;
    private static final String MESSAGE = "연결할 학교는 요청한 학교 유형과 다릅니다";

    public SchoolTypeMismatchException(String type) {
        super(MESSAGE);
        this.type = type;
    }
}
