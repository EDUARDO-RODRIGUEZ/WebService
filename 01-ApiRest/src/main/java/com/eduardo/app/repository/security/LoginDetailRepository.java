package com.eduardo.app.repository.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.eduardo.app.entity.ClienteEntity;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.repository.cliente.ClienteRepository;
import com.eduardo.app.repository.cuenta.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDetailRepository {
    @Autowired
    private ClienteRepository clienteRepository;

    public UserDetails findByCi(String ci) {
        Optional<ClienteEntity> cuenta = clienteRepository.findById(ci);
        if (cuenta.isEmpty()) {
            throw new UsernameNotFoundException("CI Account Found");
        }
        return new User(cuenta.get().getCi(), cuenta.get().getPassword(), Collections.emptyList());
    }
}
