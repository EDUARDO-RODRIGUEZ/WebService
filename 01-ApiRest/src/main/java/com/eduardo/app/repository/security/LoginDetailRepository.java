package com.eduardo.app.repository.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.repository.cuenta.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDetailRepository {
    @Autowired
    private CuentaRepository cuentaRepository;

    public UserDetails findByNroAccount(String nroAccount) {
        Optional<CuentaEntity> cuenta = cuentaRepository.findById(nroAccount);
        if (cuenta.isEmpty()) {
            throw new UsernameNotFoundException("Nor nro Account Found");
        }
        return new User(cuenta.get().getNro(), cuenta.get().getPassword(), Collections.emptyList());
    }
}
