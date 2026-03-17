package com.autoever.recall.user.service.domain;

public record UserUpdateCommand(
        String name,
        String phone,
        String address
) {
}
