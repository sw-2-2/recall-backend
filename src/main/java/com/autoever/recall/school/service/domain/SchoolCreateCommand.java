package com.autoever.recall.school.service.domain;

public record SchoolCreateCommand(
        String name,
        SchoolType type,
        String address
) {
}
