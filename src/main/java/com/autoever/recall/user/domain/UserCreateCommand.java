package com.autoever.recall.user.domain;

public record UserCreateCommand(
        String email,
        String password,
        String name,
        String phone,
        String address
) {
}
