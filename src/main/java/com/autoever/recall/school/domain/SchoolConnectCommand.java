package com.autoever.recall.school.domain;

public record SchoolConnectCommand(
        long id,
        int graduationYear
) implements SchoolCommandContent {
}
