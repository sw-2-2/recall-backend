package com.autoever.recall.user.domain;

public record UserCreateCommand(
        String name,
        String phone,
        String address
) {
}
