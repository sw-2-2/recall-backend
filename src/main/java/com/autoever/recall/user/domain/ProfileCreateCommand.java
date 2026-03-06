package com.autoever.recall.user.domain;

public record ProfileCreateCommand(
        String name,
        String phone,
        String address
) {
}
