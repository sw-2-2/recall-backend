package com.autoever.recall.user.service;

import com.autoever.recall.user.domain.ProfileCreateCommand;
import com.autoever.recall.user.domain.User;
import com.autoever.recall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User registerProfile(ProfileCreateCommand command) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("해당하는 유저를 찾을 수 없습니다. email: " + email));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findByIdWithProfileAndSchools(id)
                .orElseThrow(() -> new IllegalStateException("해당하는 유저를 찾을 수 없습니다. id: " + id));
    }
}
