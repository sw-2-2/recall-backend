package com.autoever.recall.school.dto;

import com.autoever.recall.userschool.domain.UserSchool;

public record SchoolMembersDto(
        Long id,
        String name,
        String phone,
        String address,
        Integer graduationYear,
        String elementarySchoolName,
        String middleSchoolName,
        String highSchoolName
) {
    public static SchoolMembersDto from(UserSchool targetUserSchool) {
        String elementarySchoolName = null;
        String middleSchoolName = null;
        String highSchoolName = null;

        return new SchoolMembersDto(
                null, null, null, null, // TODO : 채우기
                targetUserSchool.getGraduationYear(),
                elementarySchoolName,
                middleSchoolName,
                highSchoolName
        );
    }
}
