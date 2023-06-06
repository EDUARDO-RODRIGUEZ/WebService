package com.eduardo.app.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SuccesResponse {

    public <T> ApiResponse createSuccessResponse() {
        return ApiResponse.builder()
                .ok(true)
                .build();
    }
    public <T> ApiResponse createSuccessResponse(String key, T data) {
        return ApiResponse.builder()
                .ok(true)
                .data(setFormat(key,data))
                .build();
    }

    public <T> Map setFormat(String key,T data) {
        return new HashMap<>() {{
            put(key, data);
        }};
    }
}
