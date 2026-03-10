package com.autoever.recall.school.dto;

import com.autoever.recall.userschool.domain.UserSchool;

import java.util.List;

public record SchoolMembersResponse(List<SchoolMembersDto> members) {
    public static SchoolMembersResponse from(List<UserSchool> userSchoolList) {
        List<SchoolMembersDto> dtoList = userSchoolList.stream()
                                                          .map(SchoolMembersDto::from)
                                                          .toList();

        return new SchoolMembersResponse(dtoList);
    }
}
