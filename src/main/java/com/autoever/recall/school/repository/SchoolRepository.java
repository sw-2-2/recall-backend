package com.autoever.recall.school.repository;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    // 학교 리스트 조회 (키워드를 포함, type='all'일 때)
    List<School> findByNameContaining(String keyword);

    // 학교 리스트 조회 (키워드를 포함, 특정 타입이 주어질 떄)
    List<School> findByTypeAndNameContaining(SchoolType type, String keyword);
}
