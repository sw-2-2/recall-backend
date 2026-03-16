package com.autoever.recall.userschool.service;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.domain.UserSchool;

import java.util.List;

public interface UserSchoolService {
    UserSchool getMySchool(Long userId, SchoolType type);

    boolean existsByUserIdAndSchoolType(Long userId, SchoolType type);

    List<UserSchool> getSchoolMembers(Long schoolId, int targetYear);

    int getMyGraduationYear(Long userId, Long schoolId);
}
