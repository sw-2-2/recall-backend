package com.autoever.recall.user.controller;

import com.autoever.recall.user.domain.Profile;
import com.autoever.recall.user.domain.User;
import com.autoever.recall.user.dto.ProfileCreateRequest;
import com.autoever.recall.user.dto.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private static final User testUser = User.builder()
            .id(1L)
            .email("test@google.com")
            .build();

    @PostMapping("/profiles")
    public ResponseEntity<UserProfileResponse> createProfile(@RequestBody @Valid ProfileCreateRequest request) {
        Profile profile = request.toDomain().toProfile();
        testUser.registerProfile(profile);
        UserProfileResponse response = UserProfileResponse.from(testUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
