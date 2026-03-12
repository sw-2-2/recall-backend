package com.autoever.recall.user.dto;

import com.autoever.recall.school.dto.SchoolTypeDto;
import com.autoever.recall.user.domain.UserSchoolCreateCommand;
import jakarta.validation.constraints.*;

public record UserSchoolCreateRequest(
        @NotBlank(message = "학교 이름은 필수값입니다")
        @Size(max = 30, message = "학교 이름은 최대 30자입니다")
        String name,

        @Size(max = 255, message = "주소는 최대 255자입니다")
        String address,

        @NotNull(message = "졸업년도는 필수값입니다")
        @Max(value = 2_100, message = "졸업년도는 2100년 초과일 수 없습니다")
        @Min(value = 1_920, message = "졸업년도는 1920년 미만일 수 없습니다")
        Integer graduationYear
) {
        public UserSchoolCreateCommand toDomain(SchoolTypeDto type) {
                return new UserSchoolCreateCommand(
                        name.trim(),
                        type.toDomain(),
                        address.trim(),
                        graduationYear
                );
        }
}
