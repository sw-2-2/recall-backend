package com.autoever.recall.user.service;

import com.autoever.recall.school.service.domain.SchoolType;
import com.autoever.recall.user.service.domain.*;
import com.autoever.recall.userschool.service.domain.UserSchool;

import java.util.List;

public interface UserService {
    User createUser(UserCreateCommand command); // TODO: JWT 이후 email 빼기
    /*
    * UserSchool 미포함
    * */
    User findByEmail(String email);

    /*
     * UserSchool 포함
     * School 포함
     * */
    User getUser();

    User updateUser(UserUpdateCommand command);

    List<UserSchool> getMySchoolMembers(Long schoolId);

    UserSchool getMySchool(SchoolType type);
    
    UserSchool connectUserAndSchool(UserSchoolConnectCommand command);

    UserSchool createSchoolAndConnectUser(UserSchoolCreateCommand command);
}
