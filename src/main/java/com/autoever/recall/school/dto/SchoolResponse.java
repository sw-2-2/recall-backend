package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.School;

import java.util.List;

public record SchoolResponse(List<SchoolDto> schools) {
    public static SchoolResponse from(List<School> schoolList) {
        List<SchoolDto> dtoList = schoolList.stream()
                                            .map(SchoolDto::from)
                                            .toList();

        return new SchoolResponse(dtoList);
    }

}
