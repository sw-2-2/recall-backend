package com.autoever.recall.userschool.service.exception;

import lombok.Getter;

@Getter
public class UserNotEnrolledException extends RuntimeException {
    private final Long userId;
    private final Long schoolId;
    private static final String MESSAGE = "해당 학교의 소속이 아닙니다";

    public UserNotEnrolledException(Long userId, Long schoolId) {
        super(MESSAGE); this.userId = userId;
        this.schoolId = schoolId;
    }
}
