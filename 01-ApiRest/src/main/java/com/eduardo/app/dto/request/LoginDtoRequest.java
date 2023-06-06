package com.eduardo.app.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;


@Getter
public class LoginDtoRequest {
    @NotBlank
    @Size(min = 16,max = 16,message = "The size min and max of nro account is 16")
    private String nroAccount;
    @NotBlank(message = "must not be empty")
    private String passwordAccount;
}
