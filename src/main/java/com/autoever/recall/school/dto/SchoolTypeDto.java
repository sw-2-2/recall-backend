package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.service.exception.InvalidSchoolTypeKeyException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

public enum SchoolTypeDto {
    ELEMENTARY("elementary"),
    MIDDLE("middle"),
    HIGH("high");

    private final String key;

    SchoolTypeDto(String key) {
        this.key = key;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

    public static SchoolTypeDto fromKey(String key) {
        return Arrays.stream(SchoolTypeDto.values())
                     .filter(it -> it.getKey().equals(key))
                     .findFirst()
                     .orElseThrow(() -> new InvalidSchoolTypeKeyException(key));
    }

    public static SchoolTypeDto from(SchoolType type) {
        return switch (type) {
            case ELEMENTARY -> SchoolTypeDto.ELEMENTARY;
            case MIDDLE -> SchoolTypeDto.MIDDLE;
            case HIGH -> SchoolTypeDto.HIGH;
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
