package com.autoever.recall.user.service.domain;

import com.autoever.recall.school.domain.SchoolType;

public record UserSchoolCreateCommand(
        String name,
        SchoolType type,
        String address,
        int graduationYear
) {
}
