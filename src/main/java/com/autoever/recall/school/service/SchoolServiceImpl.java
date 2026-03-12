package com.autoever.recall.school.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolCreateCommand;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.repository.SchoolRepository;
import com.autoever.recall.userschool.domain.UserSchool;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    @Override
    public List<School> searchSchools(String keyword, boolean isAllType, SchoolType type) {
        if(isAllType) {
            return schoolRepository.findByNameContaining(keyword);
        }
        return schoolRepository.findByTypeAndNameContaining(type, keyword);
    }

    @Override
    public List<School> getSchools(boolean isAllType, SchoolType type) {
        if(isAllType) {
            return schoolRepository.findAll();
        }
        return schoolRepository.findByType(type);
    }

    @Override
    public School getSchool(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("해당 학교를 찾을 수 없습니다. ID: " + schoolId));
    }

    @Override
    @Transactional
    public School createSchool(SchoolCreateCommand command) {
        School school = School.builder()
                .name(command.name())
                .type(command.type())
                .address(command.address())
                .build();
        return schoolRepository.save(school);
    }
}
