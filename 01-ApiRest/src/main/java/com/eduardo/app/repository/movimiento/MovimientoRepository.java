package com.eduardo.app.repository.movimiento;

import com.eduardo.app.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Long> {
}
