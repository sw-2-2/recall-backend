package com.autoever.recall.user.repository;

import com.autoever.recall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    /*
    * 해당 email의 User 찾기
    * UserSchool 미포함
    * */
    Optional<User> findByEmail(String email);

    /*
    * 해당 id의 User 찾기
    * UserSchool 포함
    * School 포함
    * */
    @Query("""
        SELECT DISTINCT u FROM User u
        LEFT JOIN FETCH u.userSchools us
        LEFT JOIN FETCH us.school s
        WHERE u.id = :id
    """)
    Optional<User> findByIdWithSchools(Long id);

    @Query("SELECT COUNT(u) > 0 FROM User u " +
            "JOIN u.userSchools us " +
            "WHERE u.id = :userId AND us.school.id = :schoolId")
    boolean isUserEnrolledInSchool(@Param("userId") Long userId, @Param("schoolId") Long schoolId);
}
