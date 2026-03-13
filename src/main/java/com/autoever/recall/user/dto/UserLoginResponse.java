package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.User;

import java.time.LocalDateTime;

public record UserLoginResponse(
        Long id,
        String email,
        String name,
        String phone,
        String address,
        LocalDateTime createdAt
) {
    public static UserLoginResponse from(User user) {
        return new UserLoginResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt()
        );
    }
}
