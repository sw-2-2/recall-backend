package com.autoever.recall.school.service.exception;

import lombok.Getter;

@Getter
public class SchoolNotFoundException extends RuntimeException {
    private final Long schoolId;
    private static final String MESSAGE = "해당 학교를 찾을 수 없습니다";

    public SchoolNotFoundException(Long schoolId) {
        super(MESSAGE);
        this.schoolId = schoolId;
    }
}
