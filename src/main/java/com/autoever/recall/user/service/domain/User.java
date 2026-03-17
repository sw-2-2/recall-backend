package com.autoever.recall.user.service.domain;

import com.autoever.recall.userschool.domain.UserSchool;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 11)
    private String phone;

    @Column(nullable = true)
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserSchool> userSchools = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void updateProfile(UserUpdateCommand command) {
        this.name = command.name();
        this.phone = command.phone();
        this.address = command.address();
    }

    public void addUserSchool(UserSchool userSchool) {
        if (this.userSchools.contains(userSchool)) {
            return;
        }
        this.userSchools.add(userSchool);
        if (userSchool.getUser() != this) {
            userSchool.updateUser(this);
        }
    }
}
