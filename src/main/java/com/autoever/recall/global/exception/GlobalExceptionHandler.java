package com.autoever.recall.global.exception;

import com.autoever.recall.user.service.exception.DuplicateEmailException;
import com.autoever.recall.user.service.exception.UserNotFoundException;
import com.autoever.recall.user.service.exception.UserSchoolAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleArgumentValidException(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        log.warn("Validation failed: {}", firstErrorMessage);

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
