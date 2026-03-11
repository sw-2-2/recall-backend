package com.autoever.recall.userschool.dto;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.dto.SchoolTypeDto;
import com.autoever.recall.userschool.domain.UserSchool;

import java.time.LocalDateTime;

public record UserSchoolDto(
        Long id,
        String type,
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
