package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.School;

public record SchoolResponse(SchoolDto school) {
    public static SchoolResponse from(School school) {
        return new SchoolResponse(SchoolDto.from(school));
    }
}
