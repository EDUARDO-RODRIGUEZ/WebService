package com.eduardo.app.repository.banco;

import com.eduardo.app.entity.BancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<BancoEntity,Long> {
}
