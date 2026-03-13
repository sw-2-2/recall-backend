package com.autoever.recall.userschool.service;

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
    private final SchoolService schoolService;

    @Override
    public UserSchool getMySchool(SchoolType type) {
        Long tempUserId = 1L; // 로그인 기능 구현 전 임시 ID

        return userSchoolRepository.findByUserIdAndSchoolType(tempUserId, type)
                                   .orElseThrow(() -> new UserSchoolNotFoundException(tempUserId, type));
    }

    @Override
    public List<UserSchool> getSchoolMembers(Long schoolId) {

        schoolService.checkSchoolExists(schoolId);

        return userSchoolRepository.findAllMembersWithDetails(schoolId);
    }
}
