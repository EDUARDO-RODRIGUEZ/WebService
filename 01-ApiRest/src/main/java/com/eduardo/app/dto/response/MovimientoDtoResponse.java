package com.eduardo.app.dto.response;

import com.eduardo.app.component.MapperData;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.entity.MovimientoEntity;

import java.util.Map;

public class MovimientoDtoResponse {
    public static Map responseDataMovimiento(MovimientoEntity movimiento) {
        return MapperData.builder()
                .add("id", movimiento.getId())
                .add("fecha", movimiento.getFecha())
                .add("monto", movimiento.getMonto())
                .add("tipoMovimiento", movimiento.getTipoMovimiento().getNombre())
                .build();
    }
}
