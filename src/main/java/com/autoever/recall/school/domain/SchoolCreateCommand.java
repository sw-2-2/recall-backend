package com.autoever.recall.school.domain;

public record SchoolCreateCommand(
        String name,
        SchoolType type,
        String address
) implements SchoolCommandContent {
}
