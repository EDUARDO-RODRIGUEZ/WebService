package com.eduardo.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class LoginDto {
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;
}
