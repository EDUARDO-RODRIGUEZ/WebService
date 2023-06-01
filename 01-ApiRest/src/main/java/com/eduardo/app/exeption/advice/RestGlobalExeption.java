package com.eduardo.app.exeption.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExeption {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap();
        List<Map<String, String>> errors = ex.getFieldErrors().stream().map((error) -> new HashMap<String, String>() {
            {
                put("field", error.getField());
                put("error", error.getDefaultMessage());
            }
        }).collect(Collectors.toList());
        response.put("ok", false);
        response.put("errors", errors);
        return response;
    }

}
