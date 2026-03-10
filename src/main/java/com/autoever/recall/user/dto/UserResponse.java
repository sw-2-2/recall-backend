package com.autoever.recall.user.dto;

import com.autoever.recall.school.dto.SchoolDto;
import com.autoever.recall.user.domain.User;
import com.autoever.recall.userschool.domain.UserSchool;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Long id,
        String email,
        String name,
        String phone,
        String address,
        LocalDateTime updatedAt,
        List<SchoolDto> schools
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getUpdatedAt(),
                user.getUserSchools().stream()
                        .map(UserSchool::getSchool)
                        .map(SchoolDto::from)
                        .toList()
        );
    }
}
