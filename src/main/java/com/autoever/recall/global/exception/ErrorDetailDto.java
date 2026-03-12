package com.autoever.recall.global.exception;

public record ErrorDetailDto(
        String field,
        String message
) {
}
