package com.eduardo.app.controller.auth;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.LoginDtoRequest;
import com.eduardo.app.service.security.JwtService;

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
    private SuccesResponse succesResponse;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDtoRequest loginDtoRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoRequest.getNroAccount(), loginDtoRequest.getPasswordAccount()));
        String token = jwtService.generateToken(loginDtoRequest.getNroAccount());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("token", token));
    }
}
