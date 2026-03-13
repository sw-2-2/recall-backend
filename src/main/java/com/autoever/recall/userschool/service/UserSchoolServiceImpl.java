package com.autoever.recall.userschool.service;

import com.autoever.recall.auth.service.AuthService;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.userschool.repository.UserSchoolRepository;
import com.autoever.recall.userschool.service.exception.UserSchoolNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserSchoolServiceImpl implements UserSchoolService {
    private final UserSchoolRepository userSchoolRepository;
    private final AuthService authService;
    private final SchoolService schoolService;

    @Override
    public UserSchool getMySchool(SchoolType type) {
        Long userId = authService.getSessionUserId();
        return userSchoolRepository.findByUserIdAndSchoolType(userId, type)
                                   .orElseThrow(() -> new UserSchoolNotFoundException(userId, type));
    }

    @Override
    public List<UserSchool> getSchoolMembers(Long schoolId) {

        schoolService.checkSchoolExists(schoolId);

        return userSchoolRepository.findAllMembersWithDetails(schoolId);
    }
}
