package com.autoever.recall.school.domain;

import com.autoever.recall.profileschool.domain.ProfileSchool;
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
@Table(name = "schools")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchoolType type;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(nullable = false)
    private String address;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 1:N 관계 (읽기 전용)
    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<ProfileSchool> profileSchools = new ArrayList<>();

    @Builder
    public School(String name, SchoolType type, String imageUrl, String address) {
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.address = address;
    }
}
