package com.autoever.recall.school.dto;

import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.user.domain.Profile;

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
        Profile profile = targetUserSchool.getUser();

        String elementarySchoolName = null;
        String middleSchoolName = null;
        String highSchoolName = null;

        for(UserSchool ps : profile.getUserSchools()) {
            switch (ps.getSchool().getType()) {
                case ELEMENTARY:
                    elementarySchoolName = ps.getSchool().getName();
                    break;
                case MIDDLE:
                    middleSchoolName = ps.getSchool().getName();
                    break;
                case HIGH:
                    highSchoolName = ps.getSchool().getName();
                    break;
            }
        }

        return new SchoolMembersDto(
                profile.getUser().getId(),
                profile.getName(),
                profile.getPhone(),
                profile.getAddress(),
                targetUserSchool.getGraduationYear(),
                elementarySchoolName,
                middleSchoolName,
                highSchoolName
        );
    }
}
