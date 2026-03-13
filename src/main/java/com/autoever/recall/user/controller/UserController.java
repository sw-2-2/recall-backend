package com.autoever.recall.user.controller;


import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.userschool.domain.UserSchool;
import com.autoever.recall.userschool.service.UserSchoolService;
import com.autoever.recall.school.dto.SchoolTypeDto;
import com.autoever.recall.user.domain.*;
import com.autoever.recall.user.dto.*;
import com.autoever.recall.user.service.UserService;
import com.autoever.recall.userschool.dto.UserSchoolDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserSchoolService userSchoolService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserCreateCommand command = request.toDomain();
        User user = userService.createUser(request.email(), request.password(), command); // 변경될 속성 제외하기 위해 password 별도
        UserCreateResponse response = UserCreateResponse.from(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser() {
        UserResponse response = UserResponse.from(userService.getUser());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        UserUpdateCommand command = request.toDomain();
        UserUpdateResponse response = UserUpdateResponse.from(userService.updateUser(command));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/schools/{type}")
    public ResponseEntity<UserSchoolDto> getMySchool(@PathVariable(value = "type") String type) {
        SchoolType schoolType = SchoolTypeDto.fromKey(type).toDomain();

        UserSchool userSchool = userSchoolService.getMySchool(schoolType);
        return ResponseEntity.ok(UserSchoolDto.from(userSchool));
    }

    @GetMapping("/schools/{id}/members")
    public ResponseEntity<SchoolMembersResponse> getMySchoolMembers(@PathVariable("id") Long id) {
        List<SchoolMemberDto> memberDtos = userService.getMySchoolMembers(id)
                .stream()
                .map(SchoolMemberDto::from)
                .toList();

        return ResponseEntity.ok(SchoolMembersResponse.from(memberDtos));
    }

    @PostMapping("/schools/{type}/connect")
    public ResponseEntity<UserSchoolDto> connectUserAndSchool(
            @PathVariable String type,
            @RequestBody @Valid UserSchoolConnectRequest request
    ) {
        SchoolTypeDto schoolType = SchoolTypeDto.fromKey(type);
        UserSchoolConnectCommand command = request.toDomain(schoolType);
        UserSchoolDto response = UserSchoolDto.from(userService.connectUserAndSchool(command));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/schools/{type}/new")
    public ResponseEntity<UserSchoolDto> createSchoolAndConnectUser(
            @PathVariable String type,
            @RequestBody @Valid UserSchoolCreateRequest request
    ) {
        SchoolTypeDto schoolType = SchoolTypeDto.fromKey(type);
        UserSchoolCreateCommand command = request.toDomain(schoolType);
        UserSchoolDto response = UserSchoolDto.from(userService.createSchoolAndConnectUser(command));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
