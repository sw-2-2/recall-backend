package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.SchoolCommandContent;
import com.autoever.recall.school.domain.SchoolConnectCommand;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SchoolConnectRequest(
        @NotNull(message = "학교 id는 필수값입니다")
        Long id,

        @NotNull(message = "졸업년도는 필수값입니다")
        @Max(value = 2_100, message = "졸업년도는 2100년 초과일 수 없습니다")
        @Min(value = 1_920, message = "졸업년도는 1920년 미만일 수 없습니다")
        Integer graduationYear
) implements SchoolRequestContent {

    @Override
    public SchoolCommandContent toDomain() {
        return new SchoolConnectCommand(id, graduationYear);
    }
}
