package com.eduardo.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MovimientoDepositoDtoRequest {
    @NotBlank
    @Size(min = 16, max = 16)
    private String nroAccount;
    @NotNull
    @Positive
    private Double monto;
}
