package com.eduardo.app.repository.cuenta;

import com.eduardo.app.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CuentaRepository extends JpaRepository<CuentaEntity,String>{
}
