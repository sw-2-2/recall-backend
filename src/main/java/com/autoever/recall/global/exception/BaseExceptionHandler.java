package com.autoever.recall.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
public abstract class BaseExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleArgumentValidException(MethodArgumentNotValidException e) {
        log.warn("Validation failed: {}", e.getMessage());
        List<ErrorDetailDto> details = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorDetailDto(error.getField(), error.getDefaultMessage()))
                .toList();
        ErrorResponse response = ErrorResponse.of("INVALID_INPUT", "요청값이 유효하지 않습니다", details);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        log.error("Internal Server Error: ", e);
        ErrorResponse response = ErrorResponse.of("SERVER_ERROR", "서버 내부 오류가 발생했습니다");
        return ResponseEntity.internalServerError().body(response);
    }
}
