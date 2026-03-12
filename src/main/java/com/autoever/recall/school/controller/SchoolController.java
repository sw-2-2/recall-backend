package com.autoever.recall.school.controller;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.dto.*;
import com.autoever.recall.school.service.SchoolService;
import com.autoever.recall.user.dto.SchoolMemberDto;
import com.autoever.recall.user.dto.SchoolMembersResponse;
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

    // 학교 리스트 조회
    @GetMapping
    public ResponseEntity<SchoolsResponse> getSchools(
            @ModelAttribute @Valid SchoolFilterParams params
    ){
        SchoolType type = params.isAllType() ? null : SchoolTypeDto.fromKey(params.type()).toDomain();

        List<School> schools = schoolService.getSchools(params.isAllType(), type);
        return ResponseEntity.ok(SchoolsResponse.from(schools));
    }

    // 학교 검색 리스트 조회
    @GetMapping("/search")
    public ResponseEntity<SchoolsResponse> findAllSchools(
            @ModelAttribute @Valid SchoolMembersSearchParams params
            ) {
        SchoolType type = params.isAllType() ? null : SchoolTypeDto.fromKey(params.type()).toDomain();

        List<School> schools = schoolService.searchSchools(params.keyword(), params.isAllType(), type);
        return ResponseEntity.ok(SchoolsResponse.from(schools));
    }

    // 학교 멤버 리스트 조회
    @GetMapping("/{id}/members")
    public ResponseEntity<SchoolMembersResponse> getSchoolMembers(@PathVariable("id") Long id) {
        List<SchoolMemberDto> userSchools = schoolService.getSchoolMembers(id)
                                                         .stream()
                                                         .map(SchoolMemberDto::from)
                                                         .toList();

        SchoolMembersResponse response = new SchoolMembersResponse(userSchools);

        return ResponseEntity.ok(response);
    }

    // 학교 세부정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> getSchool(@PathVariable("id") Long id) {
        School school = schoolService.getSchool(id);

        return ResponseEntity.ok(SchoolDto.from(school));
    }
}
