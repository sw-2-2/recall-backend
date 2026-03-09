package com.autoever.recall.user.domain;

public record ProfileCreateCommand(
        String name,
        String phone,
        String address
) {
    public Profile toProfile() {
        return Profile.builder()
                .name(name)
                .phone(phone)
                .address(address)
                .build();
    }
}
