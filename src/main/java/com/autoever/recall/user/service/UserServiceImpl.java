package com.autoever.recall.user.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.user.domain.*;
import com.autoever.recall.user.repository.UserRepository;
import com.autoever.recall.userschool.domain.UserSchool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SchoolService schoolService;

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

    /*
     * 1. User가 이미 type의 학교를 갖고 있는지 검사
     * 2. 연결할 학교가 존재하는 지 검사
     * 3. UserSchool을 생성 후 연결
     * */
    @Override
    public UserSchool connectUserAndSchool(SchoolType type, UserSchoolConnectCommand command) {
        User user = getUser();
        if (user.hasSchoolType(type)) {
            throw new IllegalStateException("이미 해당 유형의 학교와 연결되어 있습니다. type: " + type);
        }
        School school = schoolService.getSchool(command.id());
        UserSchool userSchool = UserSchool.builder()
                .user(user)
                .school(school)
                .graduationYear(command.graduationYear())
                .build();
        user.addUserSchool(userSchool);
        return userSchool;
    }
}
