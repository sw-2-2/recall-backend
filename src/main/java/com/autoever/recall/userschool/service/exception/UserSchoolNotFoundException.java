package com.autoever.recall.userschool.service.exception;

import com.autoever.recall.school.domain.SchoolType;
import lombok.Getter;

@Getter
public class UserSchoolNotFoundException extends RuntimeException {
    private final Long userId;
    private final SchoolType type;
    private static final String MESSAGE = "해당 유형의 등록된 학교 정보가 없습니다";

    public UserSchoolNotFoundException(Long userId, SchoolType type) {
        super(MESSAGE);
        this.userId = userId;
        this.type = type;
    }
}
