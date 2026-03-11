package com.autoever.recall.school.repository;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    // 학교 리스트 조회 (키워드를 포함, type='all'일 때)
    List<School> findByNameContaining(String keyword);

    // 학교 리스트 조회 (키워드를 포함, 특정 타입이 주어질 떄)
    List<School> findByTypeAndNameContaining(SchoolType type, String keyword);

    // 특정 타입의 학교 리스트 조회
    List<School> findByType(SchoolType type);
}
