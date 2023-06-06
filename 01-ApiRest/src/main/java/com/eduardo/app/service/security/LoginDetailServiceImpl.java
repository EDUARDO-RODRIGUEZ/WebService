package com.eduardo.app.service.security;

import com.eduardo.app.repository.security.LoginDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailServiceImpl implements UserDetailsService {
    @Autowired
    private LoginDetailRepository loginDetailRepository;
    @Override
    public UserDetails loadUserByUsername(String nroAccount) throws UsernameNotFoundException {
        return loginDetailRepository.findByNroAccount(nroAccount);
    }

}
