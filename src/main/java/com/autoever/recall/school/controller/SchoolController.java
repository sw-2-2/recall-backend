package com.autoever.recall.school.controller;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.dto.*;
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

    @GetMapping
    public ResponseEntity<SchoolResponse> getSchools(
            @ModelAttribute @Valid SchoolFilterParams params
    ){
        SchoolType type = params.isAllType() ? null : SchoolTypeDto.fromKey(params.type()).toDomain();

        List<School> schools = schoolService.getSchools(params.isAllType(), type);
        return ResponseEntity.ok(SchoolResponse.from(schools));
    }

    // 학교 검색 리스트 조회
    @GetMapping("/search")
    public ResponseEntity<SchoolResponse> findAllSchools(
            @Valid @ModelAttribute SchoolMembersSearchParams params
            ) {
        SchoolType type = params.isAllType() ? null : SchoolTypeDto.fromKey(params.type()).toDomain();

        List<School> schools = schoolService.searchSchools(params.keyword(), params.isAllType(), type);
        return ResponseEntity.ok(SchoolResponse.from(schools));
    }

    // 학교 멤버 리스트 조회
    @GetMapping("/{id}/members")
    public ResponseEntity<SchoolMembersResponse> getSchoolMembers(@PathVariable("id") Long id) {
        List<SchoolMemberResponse> userSchools = schoolService.getSchoolMembers(id)
                .stream()
                .map(SchoolMemberResponse::from)
                .toList();

        SchoolMembersResponse response = new SchoolMembersResponse(userSchools);

        return ResponseEntity.ok(response);
    }
}
