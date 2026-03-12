package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.School;

import java.util.List;

public record SchoolsResponse(List<SchoolDto> schools) {
    public static SchoolsResponse from(List<School> schools) {
        List<SchoolDto> dtoList = schools.stream()
                                            .map(SchoolDto::from)
                                            .toList();

        return new SchoolsResponse(dtoList);
    }

}
