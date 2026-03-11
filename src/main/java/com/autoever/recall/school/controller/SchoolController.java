package com.autoever.recall.school.controller;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.dto.SchoolMembersResponse;
import com.autoever.recall.school.dto.SchoolMembersSearchParams;
import com.autoever.recall.school.dto.SchoolResponse;
import com.autoever.recall.school.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    // 학교 검색 리스트 조회
    @GetMapping("/search")
    public ResponseEntity<SchoolResponse> findAllSchools(
            @Valid @ModelAttribute SchoolMembersSearchParams params
            ) {
        List<School> schools = schoolService.searchSchools(params);
        return ResponseEntity.ok(SchoolResponse.from(schools));
    }

    // 학교 멤버 리스트 조회
    @GetMapping("/{id}/members")
    public ResponseEntity<SchoolMembersResponse> findAllMembers(@PathVariable Long id) {
        return ResponseEntity.ok(null); // TODO
    }
}
