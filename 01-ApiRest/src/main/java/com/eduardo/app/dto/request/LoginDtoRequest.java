package com.eduardo.app.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;


@Getter
public class LoginDtoRequest {
    @NotBlank
    private String ci;
    @NotBlank
    private String password;
}
