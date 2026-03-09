package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.Profile;

public record ProfileDto(
        String name,
        String phone,
        String address
) {
    public static ProfileDto from(Profile profile) {
        return new ProfileDto(
                profile.getName(),
                profile.getPhone(),
                profile.getAddress()
        );
    }
}
