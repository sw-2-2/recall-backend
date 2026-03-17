package com.autoever.recall.user.controller.dto;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.user.service.domain.User;
import com.autoever.recall.userschool.service.domain.UserSchool;

public record SchoolMemberDto(
        Long id,
        String name,
        String phone,
        String address,
        int graduationYear,
        String elementarySchoolName,
        String middleSchoolName,
        String highSchoolName
) {
    public static SchoolMemberDto from(UserSchool targetUserSchool) {
        User user = targetUserSchool.getUser();
        String elementary = null;
        String middle = null;
        String high = null;

        // Fetch Join으로 이미 로딩된 데이터이므로 추가 쿼리 없이 순회 가능
        for (UserSchool us : user.getUserSchools()) {
            School school = us.getSchool();
            if (school == null) continue;

            switch (school.getType()) {
                case ELEMENTARY -> elementary = school.getName();
                case MIDDLE -> middle = school.getName();
                case HIGH -> high = school.getName();
            }
        }

        return new SchoolMemberDto(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                targetUserSchool.getGraduationYear(),
                elementary,
                middle,
                high
        );
    }
}
