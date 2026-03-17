package com.autoever.recall.user.service;

import com.autoever.recall.auth.service.SecuritySessionService;
import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolCreateCommand;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.user.repository.UserRepository;
import com.autoever.recall.user.service.domain.*;
import com.autoever.recall.user.service.exception.DuplicateEmailException;
import com.autoever.recall.user.service.exception.UserNotFoundException;
import com.autoever.recall.user.service.exception.UserSchoolAlreadyExistsException;
import com.autoever.recall.user.service.exception.*;
import com.autoever.recall.userschool.service.domain.UserSchool;
import com.autoever.recall.userschool.service.UserSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SecuritySessionService securitySessionService;
    private final UserSchoolService userSchoolService;
    private final SchoolService schoolService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(UserCreateCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new DuplicateEmailException(command.email());
        }

        String encodedPassword = passwordEncoder.encode(command.password());

        User user = User.builder()
                .email(command.email())
                .password(encodedPassword)
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

    @Override
    public User getUser() {
        Long id = securitySessionService.getSessionUserId();
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateCommand command) {
        User user = getUser();
        user.updateProfile(command);
        return user;
    }

    @Override
    public List<UserSchool> getMySchoolMembers(Long schoolId) {
        Long id = securitySessionService.getSessionUserId();

        // userId/schoolId 존재 점검 및 사용자의 graduationYear 추출
        int myGraduationYear = userSchoolService.getMyGraduationYear(id, schoolId);

        return userSchoolService.getSchoolMembers(schoolId, myGraduationYear);
    }

    @Override
    public UserSchool getMySchool(SchoolType type) {
        Long id = securitySessionService.getSessionUserId();
        return userSchoolService.getMySchool(id, type);
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
        if (userSchoolService.existsByUserIdAndSchoolType(user.getId(), command.type())) {
            throw new UserSchoolAlreadyExistsException(command.type().name());
        }

        School school = schoolService.getSchool(command.id());
        if (!school.getType().equals(command.type())) {
            throw new SchoolTypeMismatchException(command.type().name());
        }

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
        if (userSchoolService.existsByUserIdAndSchoolType(user.getId(), command.type())) {
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
