package com.autoever.recall.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SchoolCreateRequest(
        @NotBlank(message = "학교 이름은 필수값입니다")
        @Size(max = 30, message = "학교 이름은 최대 30자입니다")
        String name,

        @NotBlank(message = "학교 타입은 필수값입니다")
        String type,

        @Size(max = 255, message = "주소는 최대 255자입니다")
        String address
) implements SchoolRequestContent {
}
