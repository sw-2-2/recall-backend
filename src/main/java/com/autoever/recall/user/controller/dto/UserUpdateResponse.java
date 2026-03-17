package com.autoever.recall.user.controller.dto;

import com.autoever.recall.user.service.domain.User;

import java.time.LocalDateTime;

public record UserUpdateResponse(
        Long id,
        String name,
        String phone,
        String address,
        LocalDateTime updatedAt
) {
    public static UserUpdateResponse from(User user) {
        return new UserUpdateResponse(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getUpdatedAt()
        );
    }
}
