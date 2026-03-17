package com.autoever.recall.user.service.domain;

import com.autoever.recall.school.service.domain.SchoolType;

public record UserSchoolConnectCommand(
        long id,
        int graduationYear,
        SchoolType type
) {
}
