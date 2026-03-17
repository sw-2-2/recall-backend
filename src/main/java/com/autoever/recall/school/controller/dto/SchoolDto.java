package com.autoever.recall.school.controller.dto;

import com.autoever.recall.school.service.domain.School;

import java.time.LocalDateTime;

public record SchoolDto(
        Long id,
        SchoolTypeDto type,
        String name,
        String imageUrl,
        String address,
        LocalDateTime createdAt
) {
    public static SchoolDto from(School school) {
        return new SchoolDto(
                school.getId(),
                SchoolTypeDto.from(school.getType()),
                school.getName(),
                school.getImageUrl(),
                school.getAddress(),
                school.getCreatedAt()
        );
    }
}
