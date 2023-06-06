package com.eduardo.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class MovimientoDtoRequest {
    @NotBlank
    @Size(min = 16, max = 16)
    private String nroAccount;
    @NotBlank
    private String passwordAccount;
    @NotNull
    @Positive
    private Double monto;
}
