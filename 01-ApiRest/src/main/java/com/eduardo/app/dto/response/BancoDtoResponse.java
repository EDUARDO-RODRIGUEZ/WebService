package com.eduardo.app.dto.response;

import com.eduardo.app.component.MapperData;
import com.eduardo.app.entity.BancoEntity;

import java.util.Map;

public class BancoDtoResponse {
    public static Map responseDataCreateBanco(BancoEntity banco) {
        return MapperData.builder()
                .add("id", banco.getId())
                .add("nombre", banco.getNombre())
                .build();
    }

    public static Map responseDataRemoveBanco(BancoEntity banco) {
        return MapperData.builder()
                .add("id", banco.getId())
                .add("nombre", banco.getNombre())
                .build();
    }
}
