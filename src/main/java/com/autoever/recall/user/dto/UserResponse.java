package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.User;

public record UserResponse(
        Long id,
        String email,
        String name,
        String phone,
        String address
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getAddress()
        );
    }
}
