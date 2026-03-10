package com.autoever.recall.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SchoolMembersSearchParams(
        String type,

        @NotBlank(message = "키워드는 필수값입니다")
        @Size(min = 1, message = "키워드는 최소 1자입니다")
        String keyword
) {
    private static final String ALL_TYPE = "all";

    public SchoolMembersSearchParams {
        if (type == null) {
            type = ALL_TYPE;
        }
    }
}
