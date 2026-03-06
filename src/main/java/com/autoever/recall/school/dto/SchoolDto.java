package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;

import java.time.LocalDateTime;

public record SchoolDto(
        Long id,
        SchoolType type,
        String name,
        String imageUrl,
        String address,
        LocalDateTime createdAt
) {
    public static SchoolDto from(School school) {
        return new SchoolDto(
                school.getId(),
                school.getType(),
                school.getName(),
                school.getImageUrl(),
                school.getAddress(),
                school.getCreatedAt()
        );
    }
}
