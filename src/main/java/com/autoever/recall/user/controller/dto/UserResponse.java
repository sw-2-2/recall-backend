package com.autoever.recall.user.controller.dto;

import com.autoever.recall.user.service.domain.User;
import com.autoever.recall.userschool.dto.UserSchoolDto;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Long id,
        String email,
        String name,
        String phone,
        String address,
        LocalDateTime updatedAt,
        List<UserSchoolDto> schools
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
                        .map(UserSchoolDto::from)
                        .toList()
        );
    }
}
