package com.eduardo.app.dto.response;

import com.eduardo.app.component.MapperData;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.entity.MovimientoEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CuentaDtoResponse {
    public static Map responseDataCuenta(CuentaEntity cuenta) {
        return MapperData.builder()
                .add("nro", cuenta.getNro())
                .add("saldo", cuenta.getSaldo())
                .add("tipoCuenta", cuenta.getTipoCuenta().getNombre())
                .add("cliente", cuenta.getCliente().getNombre().concat(" ") + cuenta.getCliente().getApellido())
                .add("banco", cuenta.getBanco().getNombre())
                .build();
    }

    public static List<Map> responseDataMovimientos(List<MovimientoEntity> movimientos) {
        List<Map> data = movimientos.stream().map((movimiento) -> {
            return new HashMap() {{
                put("id", movimiento.getId());
                put("fecha", movimiento.getFecha());
                put("monto", movimiento.getMonto());
                put("tipoMovimiento", movimiento.getTipoMovimiento().getNombre());
            }};
        }).collect(Collectors.toList());
        return data;
    }
}
