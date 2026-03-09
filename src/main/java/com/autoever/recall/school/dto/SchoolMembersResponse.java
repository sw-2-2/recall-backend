package com.autoever.recall.school.dto;

import com.autoever.recall.profileschool.domain.ProfileSchool;

import java.util.List;

public record SchoolMembersResponse(List<SchoolMembersDto> members) {
    public static SchoolMembersResponse from(List<ProfileSchool> profileSchoolList) {
        List<SchoolMembersDto> dtoList = profileSchoolList.stream()
                                                          .map(SchoolMembersDto::from)
                                                          .toList();

        return new SchoolMembersResponse(dtoList);
    }
}
