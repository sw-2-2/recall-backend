package com.autoever.recall.user.domain;

public record ProfileUpdateCommand(
        String name,
        String phone,
        String address
) {
}
