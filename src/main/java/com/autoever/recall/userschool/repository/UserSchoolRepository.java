package com.autoever.recall.userschool.repository;

import com.autoever.recall.userschool.domain.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {
    // 학교 멤버 리스트 조회
    @Query("SELECT ps FROM ProfileSchool ps " +
            "JOIN FETCH ps.profile p " +
            "JOIN FETCH p.user u " +
            "WHERE ps.school.id = :schoolId")
    List<UserSchool> findMembersBySchoolId(@Param("schoolId") Long schoolId);
}
