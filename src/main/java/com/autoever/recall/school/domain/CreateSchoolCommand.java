package com.autoever.recall.school.domain;

public record CreateSchoolCommand(
        String name,
        SchoolType type,
        String address
) {
}
