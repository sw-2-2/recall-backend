package com.autoever.recall.profileschool.repository;

import com.autoever.recall.profileschool.domain.ProfileSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileSchoolRepository extends JpaRepository<ProfileSchool, Long> {
    // 학교 멤버 리스트 조회
    @Query("SELECT ps FROM ProfileSchool ps " +
            "JOIN FETCH ps.profile p " +
            "JOIN FETCH p.user u " +
            "WHERE ps.school.id = :schoolId")
    List<ProfileSchool> findMembersBySchoolId(@Param("schoolId") Long schoolId);
}
