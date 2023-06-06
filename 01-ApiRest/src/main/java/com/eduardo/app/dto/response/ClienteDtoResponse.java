package com.eduardo.app.dto.response;

import com.eduardo.app.component.MapperData;
import com.eduardo.app.entity.ClienteEntity;
import com.eduardo.app.entity.CuentaEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteDtoResponse {
    public static Map responseDataCreateCliente(ClienteEntity cliente) {
        return MapperData.builder()
                .add("ci", cliente.getCi())
                .add("nombre", cliente.getNombre())
                .add("apellido", cliente.getApellido())
                .build();
    }

    public static Map responseDataUpdateCliente(ClienteEntity cliente) {
        return MapperData.builder()
                .add("ci", cliente.getCi())
                .add("nombre", cliente.getNombre())
                .add("apellido", cliente.getApellido())
                .build();
    }

    public static List<Map> responseDataCuentaCliente(List<CuentaEntity> cuentas) {
        List<Map> data = cuentas.stream().map((cuenta) -> {
            return new HashMap() {{
                put("nro", cuenta.getNro());
                put("saldo", cuenta.getSaldo());
                put("tipoCuenta", cuenta.getTipoCuenta().getNombre());
                put("banco", cuenta.getBanco().getNombre());
            }};
        }).collect(Collectors.toList());
        return data;
    }

}