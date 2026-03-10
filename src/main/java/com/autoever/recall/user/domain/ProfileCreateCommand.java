package com.autoever.recall.user.domain;

import com.autoever.recall.school.domain.SchoolCommandContent;

import java.util.List;

public record ProfileCreateCommand(
        String name,
        String phone,
        String address,
        List<SchoolCommandContent> schools
) {
}
