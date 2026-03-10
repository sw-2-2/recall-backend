package com.autoever.recall.user.service;

import com.autoever.recall.user.domain.User;
import com.autoever.recall.user.domain.UserUpdateCommand;

public interface UserService {
    /*
    * UserSchool 미포함
    * */
    User findByEmail(String email);

    /*
     * UserSchool 미포함
     * */
    User findById(Long id);

    /*
     * UserSchool 포함
     * School 포함
     * */
    User getUser();

    User updateUser(UserUpdateCommand command);
}
