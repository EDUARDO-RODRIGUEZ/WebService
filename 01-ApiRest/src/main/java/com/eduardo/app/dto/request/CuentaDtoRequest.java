package com.eduardo.app.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CuentaDtoRequest {
    @NotBlank
    private String nro;
    @NotBlank
    private String password;
    @NotBlank
    private String ci;
    @NotNull
    @Positive
    private Long tipoCuentaId;
    @NotNull
    @Positive
    private Long bancoId;
}
