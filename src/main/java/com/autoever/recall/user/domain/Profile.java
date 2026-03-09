package com.autoever.recall.user.domain;

import com.autoever.recall.profileschool.domain.ProfileSchool;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile")
    private User user;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 11)
    private String phone;

    @Column(nullable = true)
    private String address;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 1:N 관계 (읽기 전용)
    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<ProfileSchool> profileSchools = new ArrayList<>();

    @Builder
    public Profile(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public void registerUser(User user) {
        this.user = user;
    }

    public void update(ProfileUpdateCommand command) {
        if (command.name() != null) {
            this.name = command.name();
        }
        if (command.phone() != null) {
            this.phone = command.phone();
        }
        if (command.address() != null) {
            this.address = command.address();
        }
    }
}
