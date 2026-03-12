package com.autoever.recall.global.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp,
        List<ErrorDetailDto> details
) {
    public static ErrorResponse of(String code, String message) {
        return  new ErrorResponse(code, message, LocalDateTime.now(), null);
    }

    public static ErrorResponse of(String code, String message, List<ErrorDetailDto> details) {
        return new ErrorResponse(code, message, LocalDateTime.now(), details);
    }
}
