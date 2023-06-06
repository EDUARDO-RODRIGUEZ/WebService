package com.eduardo.app.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private boolean ok;
    private T data;
    private String message;
    private List<?> errors;
}
