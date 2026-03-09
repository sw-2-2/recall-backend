package com.autoever.recall.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SchoolMembersSearchParams(
        String type,

        @NotBlank
        @Size(min = 1)
        String keyword
) {
    private static final String ALL_TYPE = "all";

    public SchoolMembersSearchParams {
        if (type == null) {
            type = ALL_TYPE;
        }
    }
}
