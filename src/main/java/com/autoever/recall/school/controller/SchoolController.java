package com.autoever.recall.school.controller;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.dto.*;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.userschool.domain.UserSchool;
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

    @GetMapping
    public ResponseEntity<SchoolResponse> getSchools(
            @Valid @ModelAttribute SchoolFilterParams params
    ){
        List<School> schools = schoolService.getSchools(params);
        return ResponseEntity.ok(SchoolResponse.from(schools));
    }

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
    public ResponseEntity<SchoolMemberListResponse> getSchoolMembers(@PathVariable("id") Long id) {
        List<UserSchool> userSchools = schoolService.getSchoolMembers(id);

        List<SchoolMemberResponse> memberResponses = userSchools.stream()
                .map(SchoolMemberResponse::from)
                .toList();

        SchoolMemberListResponse response = new SchoolMemberListResponse(memberResponses);

        return ResponseEntity.ok(response);
    }
}
