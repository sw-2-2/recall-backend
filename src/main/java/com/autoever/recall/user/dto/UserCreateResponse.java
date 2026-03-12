package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.User;

import java.time.LocalDateTime;

public record UserCreateResponse(
        Long id,
        String email,
        String name,
        String phone,
        String address,
        LocalDateTime createdAt
) {
    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt()
        );
    }
}
