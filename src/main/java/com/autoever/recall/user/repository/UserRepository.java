package com.autoever.recall.user.repository;

import com.autoever.recall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    /*
    * 해당 email의 User 찾기
    * Profile 미포함
    * */
    Optional<User> findByEmail(String email);

    /*
    * 해당 id의 User 찾기
    * Profile 포함
    * */
    @Query("""
        SELECT u FROM User
        LEFT JOIN FETCH u.profile
        WHERE u.id = :id
    """)
    Optional<User> findByIdWithProfile(@Param("id") Long id);
}
