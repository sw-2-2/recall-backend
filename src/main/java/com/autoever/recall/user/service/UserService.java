package com.autoever.recall.user.service;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.user.domain.User;
import com.autoever.recall.user.domain.UserCreateCommand;
import com.autoever.recall.user.domain.UserSchoolConnectCommand;
import com.autoever.recall.user.domain.UserUpdateCommand;

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

    User connectUserAndSchool(UserSchoolConnectCommand command, SchoolType type);
}
