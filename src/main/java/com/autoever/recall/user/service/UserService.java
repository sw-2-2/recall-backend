package com.autoever.recall.user.service;

import java.util.List;
import com.autoever.recall.user.domain.*;
import com.autoever.recall.userschool.domain.UserSchool;

public interface UserService {
    User createUser(String email, UserCreateCommand command); // TODO: JWT 이후 email 빼기

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
    
    UserSchool connectUserAndSchool(UserSchoolConnectCommand command);

    UserSchool createSchoolAndConnectUser(UserSchoolCreateCommand command);
}
