package com.autoever.recall.auth.service.exception;

public class UnAuthorizedException extends RuntimeException {
    private static final String MESSAGE = "인증이 필요합니다";

    public UnAuthorizedException() {
        super(MESSAGE);
    }
}
