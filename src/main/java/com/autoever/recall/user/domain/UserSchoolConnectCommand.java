package com.autoever.recall.user.domain;

import com.autoever.recall.school.domain.SchoolType;

public record UserSchoolConnectCommand(
        long id,
        int graduationYear,
        SchoolType type
) {
}
