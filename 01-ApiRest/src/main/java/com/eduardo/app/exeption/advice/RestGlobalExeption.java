package com.eduardo.app.exeption.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExeption {

    @Autowired
    private ErrorResponse errorResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap();
        List<Map<String, String>> errors = ex.getFieldErrors().stream().map((error) -> new HashMap<String, String>() {
            {
                put("field", error.getField());
                put("error", error.getDefaultMessage());
            }
        }).collect(Collectors.toList());
        return errorResponse.createErrorResponse("Bad Request", errors);
    }

}
