package com.autoever.recall.user.dto;

import com.autoever.recall.school.dto.SchoolDto;

import java.util.List;

public record ProfileDto(
        String name,
        String phone,
        String address,
        List<SchoolDto> schools
) {
}
