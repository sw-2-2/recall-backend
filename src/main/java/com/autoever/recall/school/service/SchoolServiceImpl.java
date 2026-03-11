package com.autoever.recall.school.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.dto.SchoolFilterParams;
import com.autoever.recall.school.dto.SchoolMembersSearchParams;
import com.autoever.recall.school.dto.SchoolTypeDto;
import com.autoever.recall.school.repository.SchoolRepository;
import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.userschool.repository.UserSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final UserSchoolRepository userSchoolRepository;

    @Override
    public List<School> searchSchools(SchoolMembersSearchParams params) {
        String keyword = params.keyword();

        if(params.isAllType()) {
            return schoolRepository.findByNameContaining(keyword);
        }

        SchoolType type = SchoolTypeDto.fromKey(params.type()).toDomain();
        return schoolRepository.findByTypeAndNameContaining(type, keyword);
    }

    @Override
    public List<School> getSchools(SchoolFilterParams params) {
        if(params.isAllType()) {
            return schoolRepository.findAll();
        }

        SchoolType type = SchoolTypeDto.fromKey(params.type()).toDomain();
        return schoolRepository.findByType(type);
    }

    @Override
    public List<UserSchool> getSchoolMembers(Long schoolId) {
        return userSchoolRepository.findAllMembersWithDetails(schoolId);
    }
}
