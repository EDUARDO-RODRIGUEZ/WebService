package com.eduardo.app.controller.auth;

import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.LoginDto;
import com.eduardo.app.service.security.JwtService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestRequestMapping.AUTHENTICATION)
public class Authentication {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        Map<String, Object> response = new HashMap();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        String token = jwtService.generateToken(loginDto.getEmail());
        response.put("ok", true);
        response.put("token", token);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
