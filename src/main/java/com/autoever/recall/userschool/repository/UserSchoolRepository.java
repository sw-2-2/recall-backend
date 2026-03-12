package com.autoever.recall.userschool.repository;

import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.domain.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {
    // 학교 멤버 리스트 조회
    @Query("SELECT DISTINCT us FROM UserSchool us " +
            "JOIN FETCH us.user u " +
            "JOIN FETCH u.userSchools uss " +
            "JOIN FETCH uss.school s " +
            "WHERE us.school.id = :schoolId")
    List<UserSchool> findAllMembersWithDetails(@Param("schoolId") Long schoolId);

    // 본인 학교 정보 조회
    @Query("SELECT us FROM UserSchool us JOIN FETCH us.school s " +
            "WHERE us.user.id = :userId AND s.type = :type")
    Optional<UserSchool> findByUserIdAndSchoolType(@Param("userId") Long userId, @Param("type") SchoolType type);
}
