package com.eduardo.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClienteDtoRequest {
    @NotBlank
    @Size(min = 7,max = 10)
    private String ci;
    @NotBlank
    @Size(min = 3,max = 20)
    private String nombre;
    @NotBlank
    @Size(min = 3,max = 20)
    private String apellido;
}
