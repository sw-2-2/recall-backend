package com.autoever.recall.user.service;

import com.autoever.recall.user.domain.User;
import com.autoever.recall.user.domain.UserCreateCommand;
import com.autoever.recall.user.domain.UserRole;
import com.autoever.recall.user.domain.UserUpdateCommand;
import com.autoever.recall.user.repository.UserRepository;
import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.userschool.service.UserSchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserSchoolServiceImpl userSchoolService;

    @Override
    @Transactional
    public User createUser(String email, UserCreateCommand command) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 유저입니다. email:" + email);
        }
        User user = User.builder()
                .email(email)
                .role(UserRole.USER)
                .name(command.name())
                .phone(command.phone())
                .address(command.address())
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("해당하는 유저를 찾을 수 없습니다. email: " + email));
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당하는 유저를 찾을 수 없습니다. id: " + id));
    }

    @Override
    public User getUser() {
        return userRepository.findByIdWithSchools(1L) // TODO: JWT로 email로 조회
                .orElseThrow(() -> new IllegalStateException("해당하는 유저를 찾을 수 없습니다. id: " + 1L));
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateCommand command) {
        User user = findById(1L); // TODO: JWT로 email로 조회
        user.update(command);
        return user;
    }

    @Override
    public List<UserSchool> getMySchoolMembers(Long schoolId) {
        Long tempUserId = 1L; // JWT 전 임시 지정

        boolean isEnrolled = userRepository.isUserEnrolledInSchool(tempUserId, schoolId);
        if(!isEnrolled) {
            throw new IllegalArgumentException(
                    String.format("사용자(ID: %d)는 해당 학교(ID: %d)의 소속이 아닙니다.", tempUserId, schoolId)
            );
        }

        return userSchoolService.getSchoolMembers(schoolId)
                                                          ;
    }
}
