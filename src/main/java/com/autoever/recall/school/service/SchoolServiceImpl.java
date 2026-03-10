package com.autoever.recall.school.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.repository.SchoolRepository;
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
    public List<School> searchSchools(SchoolType type, String keyword) {
        if(type == null) {
            return schoolRepository.findByNameContaining(keyword);
        }
        return schoolRepository.findByTypeAndNameContaining(type, keyword);
    }
}
