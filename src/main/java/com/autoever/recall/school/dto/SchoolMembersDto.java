package com.autoever.recall.school.dto;

import com.autoever.recall.profileschool.domain.ProfileSchool;
import com.autoever.recall.school.domain.SchoolType;
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
    public static SchoolMembersDto from(ProfileSchool targetProfileSchool) {
        Profile profile = targetProfileSchool.getProfile();

        String elementarySchoolName = null;
        String middleSchoolName = null;
        String highSchoolName = null;

        for(ProfileSchool ps : profile.getProfileSchools()) {
            if(ps.getSchool().getType() == SchoolType.ELEMENTARY) {
                elementarySchoolName = ps.getSchool().getName();
            } else if (ps.getSchool().getType() == SchoolType.MIDDLE) {
                middleSchoolName = ps.getSchool().getName();
            } else if (ps.getSchool().getType() == SchoolType.HIGH) {
                highSchoolName = ps.getSchool().getName();
            }
        }

        return new SchoolMembersDto(
                profile.getUser().getId(),
                profile.getName(),
                profile.getPhone(),
                profile.getAddress(),
                targetProfileSchool.getGraduationYear(),
                elementarySchoolName,
                middleSchoolName,
                highSchoolName
        );
    }
}
