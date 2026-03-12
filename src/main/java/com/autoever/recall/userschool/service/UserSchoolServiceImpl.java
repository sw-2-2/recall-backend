package com.autoever.recall.userschool.service;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.userschool.repository.UserSchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserSchoolServiceImpl implements UserSchoolService {
    private final UserSchoolRepository userSchoolRepository;

    @Override
    public UserSchool getMySchool(SchoolType type) {
        Long tempUserId = 1L; // 로그인 기능 구현 전 임시 ID

        return userSchoolRepository.findByUserIdAndSchoolType(tempUserId, type)
                                   .orElseThrow(() -> new EntityNotFoundException("해당 타입의 학교 등록 정보가 없습니다. type: " + type));
    }
}
