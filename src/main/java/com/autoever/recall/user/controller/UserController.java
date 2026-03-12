package com.autoever.recall.user.controller;

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

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserCreateCommand command = request.toDomain();
        User user = userService.createUser(request.email(), command);
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
