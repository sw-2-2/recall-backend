package com.autoever.recall.userschool.service;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.service.exception.UserNotEnrolledException;
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

    @Override
    public UserSchool getMySchool(Long userId, SchoolType type) {
        return userSchoolRepository.findByUserIdAndSchoolType(userId, type)
                                   .orElseThrow(() -> new UserSchoolNotFoundException(userId, type));
    }

    @Override
    public boolean existsByUserIdAndSchoolType(Long userId, SchoolType type) {
        return userSchoolRepository.findByUserIdAndSchoolType(userId, type).isPresent();
    }

    @Override
    public List<UserSchool> getSchoolMembers(Long schoolId, int targetYear) {
        return userSchoolRepository.findAllMembersWithDetails(schoolId, targetYear - 2, targetYear + 2);
    }

    @Override
    public int getMyGraduationYear(Long userId, Long schoolId) {
        return userSchoolRepository.findByUserIdAndSchoolId(userId, schoolId)
                .map(UserSchool::getGraduationYear)
                .orElseThrow(() -> new UserNotEnrolledException(userId, schoolId));
    }
}
