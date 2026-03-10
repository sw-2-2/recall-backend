package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.User;

public record UserProfileResponse(
        Long id,
        String email,
        ProfileDto profile) {
    public static UserProfileResponse from(User user) {
        return new  UserProfileResponse(
                user.getId(),
                user.getEmail(),
                ProfileDto.from(user.getProfile())
        );
    }
}
