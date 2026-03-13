package com.autoever.recall.auth.service.exception;

public class UnAuthorizedException extends RuntimeException {
    public static final String MESSAGE = "인증 정보가 없가나 만료됐습니다";

    public UnAuthorizedException() {
        super(MESSAGE);
    }
}
