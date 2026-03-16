package com.autoever.recall.user.service;

import com.autoever.recall.auth.service.SecuritySessionService;
import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolCreateCommand;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.user.domain.*;
import com.autoever.recall.user.repository.UserRepository;
import com.autoever.recall.user.service.exception.DuplicateEmailException;
import com.autoever.recall.user.service.exception.UserNotEnrolledException;
import com.autoever.recall.user.service.exception.UserNotFoundException;
import com.autoever.recall.user.service.exception.UserSchoolAlreadyExistsException;
import com.autoever.recall.userschool.domain.UserSchool;
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

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    public User getUser() {
        Long id = securitySessionService.getSessionUserId();
        return userRepository.findByIdWithSchools(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateCommand command) {
        Long id = securitySessionService.getSessionUserId();
        User user = findById(id);
        user.update(command);
        return user;
    }

    @Override
    public List<UserSchool> getMySchoolMembers(Long schoolId) {
        Long id = securitySessionService.getSessionUserId();

        // 학교 존재 여부 확인
        schoolService.checkSchoolExists(schoolId);

        // 사용자와 학교 연관 정보 조회
        User user = userRepository.findByIdWithSchools(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        // 해당 학교 소속 여부 확인 및 내 졸업 연도 추출
        int myGraduationYear = user.getUserSchools().stream()
                .filter(us -> us.getSchool().getId().equals(schoolId))
                .findFirst()
                .orElseThrow(() -> new UserNotEnrolledException(id, schoolId))
                .getGraduationYear();

        return userSchoolService.getSchoolMembers(schoolId, myGraduationYear);
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
