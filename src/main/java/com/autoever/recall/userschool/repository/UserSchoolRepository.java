package com.autoever.recall.userschool.repository;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.service.domain.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {
    // 학교 ID로 멤버 리스트 조회
    @Query("""
            SELECT DISTINCT us FROM UserSchool us
            JOIN FETCH us.user u
            WHERE us.school.id = :schoolId
            AND us.graduationYear BETWEEN :startYear AND :endYear""")
    List<UserSchool> findAllMembersWithDetails(
            @Param("schoolId") Long schoolId,
            @Param("startYear") int startYear,
            @Param("endYear") int endYear
    );

    // 본인 학교 정보 조회
    @Query("""
            SELECT us
            FROM UserSchool us
            JOIN FETCH us.school s
            WHERE us.user.id = :userId AND s.type = :type
    """)
    Optional<UserSchool> findByUserIdAndSchoolType(@Param("userId") Long userId, @Param("type") SchoolType type);

    // 특정 유저와 특정 학교의 연결 정보 단건 조회
    Optional<UserSchool> findByUserIdAndSchoolId(Long userId, Long schoolId);
}
