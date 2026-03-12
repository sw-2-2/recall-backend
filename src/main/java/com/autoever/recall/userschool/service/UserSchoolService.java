package com.autoever.recall.userschool.service;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.domain.UserSchool;

public interface UserSchoolService {
    UserSchool getMySchool(SchoolType type);
}
