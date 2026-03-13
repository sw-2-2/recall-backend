package com.autoever.recall.auth.service.domain;

import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUserDetails implements UserDetails {
    @Getter
    private final Long id;          // DB의 PK 값
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public @NonNull String getUsername() {
        return email;
    }
}
