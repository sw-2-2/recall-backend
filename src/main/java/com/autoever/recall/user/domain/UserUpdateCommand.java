package com.autoever.recall.user.domain;

public record UserUpdateCommand(
        String name,
        String phone,
        String address
) {
}
