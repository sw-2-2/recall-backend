package com.autoever.recall.user.controller;

import com.autoever.recall.user.dto.ProfileCreateRequest;
import com.autoever.recall.user.dto.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @PostMapping("/profiles")
    public ResponseEntity<UserProfileResponse> createProfile(@RequestBody @Valid ProfileCreateRequest request) {
        return null;
    }
}
