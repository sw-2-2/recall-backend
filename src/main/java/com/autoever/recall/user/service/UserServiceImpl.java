package com.autoever.recall.user.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolCreateCommand;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.user.domain.*;
import com.autoever.recall.user.repository.UserRepository;
import com.autoever.recall.user.service.exception.DuplicateEmailException;
import com.autoever.recall.user.service.exception.UserNotFoundException;
import com.autoever.recall.user.service.exception.UserSchoolAlreadyExistsException;
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
            throw new DuplicateEmailException(email);
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
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    public User getUser() {
        return userRepository.findByIdWithSchools(1L) // TODO: JWT로 email로 조회
                .orElseThrow(() -> new UserNotFoundException("1"));
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
    @Transactional
    public UserSchool connectUserAndSchool(UserSchoolConnectCommand command) {
        User user = getUser();
        if (user.hasSchoolType(command.type())) {
            throw new UserSchoolAlreadyExistsException(command.type().name());
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

    /*
    * 1. User가 이미 type의 학교를 갖고 있는지 검사
    * 2. 새로운 학교를 생성
    * 3. UserSchool을 생성 후 연결
    * */
    @Override
    @Transactional
    public UserSchool createSchoolAndConnectUser(UserSchoolCreateCommand command) {
        User user = getUser();
        if (user.hasSchoolType(command.type())) {
            throw new UserSchoolAlreadyExistsException(command.type().name());
        }
        School school = schoolService.createSchool(
                new SchoolCreateCommand(command.name(), command.type(), command.address())
        );
        UserSchool userSchool = UserSchool.builder()
                .user(user)
                .school(school)
                .graduationYear(command.graduationYear())
                .build();
        user.addUserSchool(userSchool);
        return userSchool;
    }
}
