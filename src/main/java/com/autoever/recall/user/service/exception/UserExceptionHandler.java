package com.autoever.recall.user.service.exception;

import com.autoever.recall.global.exception.BaseExceptionHandler;
import com.autoever.recall.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.autoever.recall.user")
public class UserExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.warn("[UserNotFound] email: {}", e.getEmail());

        ErrorResponse response = ErrorResponse.of("USER_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        log.warn("[DuplicateEmail] email: {}", e.getEmail());

        ErrorResponse response = ErrorResponse.of("USER_EMAIL_DUPLICATED", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserSchoolAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserSchoolAlreadyExistsException(UserSchoolAlreadyExistsException e) {
        log.warn("[UserSchoolAlreadyExists] type: {}", e.getType());

        ErrorResponse response = ErrorResponse.of("USER_SCHOOL_EXISTS", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
