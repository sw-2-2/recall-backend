package com.autoever.recall.auth.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication login(String email, String password);
}
