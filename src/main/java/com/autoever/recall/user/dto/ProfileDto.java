package com.autoever.recall.user.dto;

import com.autoever.recall.school.dto.SchoolDto;
import com.autoever.recall.user.domain.Profile;

import java.util.List;

public record ProfileDto(
        String name,
        String phone,
        String address,
        List<SchoolDto> schools
) {
    public static ProfileDto from(Profile profile) {
        return new ProfileDto(
                profile.getName(),
                profile.getPhone(),
                profile.getAddress(),
                List.of() // TODO: SchoolDto 추가
        );
    }
}
