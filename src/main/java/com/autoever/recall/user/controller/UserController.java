package com.autoever.recall.user.controller;

import com.autoever.recall.user.domain.UserUpdateCommand;
import com.autoever.recall.user.dto.UserResponse;
import com.autoever.recall.user.dto.UserUpdateRequest;
import com.autoever.recall.user.dto.UserUpdateResponse;
import com.autoever.recall.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
}
