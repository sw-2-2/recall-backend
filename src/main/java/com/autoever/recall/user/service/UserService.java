package com.autoever.recall.user.service;

import com.autoever.recall.user.domain.UserCreateCommand;
import com.autoever.recall.user.domain.User;

public interface UserService {
    User findByEmail(String email);

    User getUser(Long id);

    User registerProfile(UserCreateCommand command);
}
