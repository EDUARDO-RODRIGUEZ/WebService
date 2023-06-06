package com.eduardo.app.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class BancoDtoRequest {
    @NotBlank
    private String nombre;
}
