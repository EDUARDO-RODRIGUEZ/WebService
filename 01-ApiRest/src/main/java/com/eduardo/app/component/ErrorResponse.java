package com.eduardo.app.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class ErrorResponse {
    public ApiResponse createErrorResponse(String message, List<?> errors) {
        return ApiResponse.builder()
                .ok(false)
                .errors(errors)
                .message(message)
                .build();
    }

    public ApiResponse createErrorResponse(String message) {
        return ApiResponse.builder()
                .ok(false)
                .errors(Collections.emptyList())
                .message(message)
                .build();
    }
}
