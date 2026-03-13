package com.autoever.recall.auth.service;

import com.autoever.recall.auth.service.domain.SessionUserDetails;
import com.autoever.recall.auth.service.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuritySessionServiceImpl implements SecuritySessionService {
    @Override
    public Long getSessionUserId() {
        return getSessionUserDetails().getId();
    }

    private SessionUserDetails getSessionUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new UnAuthorizedException();
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof SessionUserDetails userDetails)) {
            throw new UnAuthorizedException();
        }
        return userDetails;
    }
}
