package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.SchoolType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum SchoolTypeDto {
    ELEMENTARY("elementary"),
    MIDDLE("middle"),
    HIGH("high");

    private final String key;

    SchoolTypeDto(String key) {
        this.key = key;
    }

    // Response: 객체를 JSON으로 보낼 때 'key'값을 사용함
    @JsonValue
    public String getKey() {
        return key;
    }

    // Request: JSON 문자열을 Enum으로 바꿀 때 이 메서드를 사용함
    @JsonCreator
    public static SchoolTypeDto fromKey(String key) {
        return Arrays.stream(SchoolTypeDto.values())
                     .filter(role -> role.getKey().equals(key))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 학교 유형(SchoolType)입니다: " + key));
    }

    public static String from(SchoolType type) {
        return switch (type) {
            case ELEMENTARY -> SchoolTypeDto.ELEMENTARY.key;
            case MIDDLE -> SchoolTypeDto.MIDDLE.key;
            case HIGH -> SchoolTypeDto.HIGH.key;
        };
    }

    public SchoolType toDomain() {
        return switch (this) {
            case ELEMENTARY -> SchoolType.ELEMENTARY;
            case MIDDLE -> SchoolType.MIDDLE;
            case HIGH -> SchoolType.HIGH;
        };
    }
}
