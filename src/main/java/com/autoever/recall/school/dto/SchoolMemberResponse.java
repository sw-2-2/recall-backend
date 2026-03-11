package com.autoever.recall.school.dto;

import com.autoever.recall.userschool.domain.UserSchool;

public record SchoolMemberResponse(
        Long id,
        String name,
        String phone,
        String address,
        int graduationYear,
        String elementarySchoolName,
        String middleSchoolName,
        String highSchoolName
) {
    public static SchoolMemberResponse from(UserSchool targetUserSchool) {
        var user = targetUserSchool.getUser();
        String elementary = null;
        String middle = null;
        String high = null;

        // Fetch Join으로 이미 로딩된 데이터이므로 추가 쿼리 없이 순회 가능
        for (UserSchool us : user.getUserSchools()) {
            var school = us.getSchool();
            if (school == null) continue;

            switch (school.getType()) {
                case ELEMENTARY -> elementary = school.getName();
                case MIDDLE -> middle = school.getName();
                case HIGH -> high = school.getName();
            }
        }

        return new SchoolMemberResponse(
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
