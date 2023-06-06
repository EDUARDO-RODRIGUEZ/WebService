package com.eduardo.app.repository.tipoMovimiento;

import com.eduardo.app.entity.TipoMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimientoEntity, Long> {
    @Query("Select tm from TipoMovimientoEntity tm where tm.nombre=:nombre")
    TipoMovimientoEntity findByName(@Param("nombre") String nombre);
}
