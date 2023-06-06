package com.eduardo.app.repository.tipoCuenta;

import com.eduardo.app.entity.TipoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCuentaRepository extends JpaRepository<TipoCuentaEntity, Long> {
}
