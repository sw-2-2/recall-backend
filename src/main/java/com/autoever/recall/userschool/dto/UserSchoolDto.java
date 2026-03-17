package com.autoever.recall.userschool.dto;

import com.autoever.recall.school.service.domain.School;
import com.autoever.recall.school.controller.dto.SchoolTypeDto;
import com.autoever.recall.userschool.service.domain.UserSchool;

import java.time.LocalDateTime;

public record UserSchoolDto(
        Long id,
        SchoolTypeDto type,
        Integer graduationYear,
        String name,
        String imageUrl,
        String address,
        LocalDateTime createdAt
) {
    public static UserSchoolDto from(UserSchool userSchool) {
        School school = userSchool.getSchool();
        return new UserSchoolDto(
                school.getId(),
                SchoolTypeDto.from(school.getType()),
                userSchool.getGraduationYear(),
                school.getName(),
                school.getImageUrl(),
                school.getAddress(),
                school.getCreatedAt()
        );
    }
}
