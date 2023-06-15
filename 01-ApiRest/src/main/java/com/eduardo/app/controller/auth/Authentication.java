package com.eduardo.app.controller.auth;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.LoginDtoRequest;
import com.eduardo.app.dto.response.ClienteDtoResponse;
import com.eduardo.app.entity.ClienteEntity;
import com.eduardo.app.repository.cliente.ClienteRepository;
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

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = RestRequestMapping.AUTHENTICATION)
public class Authentication {
    @Autowired
    private SuccesResponse succesResponse;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDtoRequest loginDtoRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoRequest.getCi(), loginDtoRequest.getPassword()));
        ClienteEntity cliente = clienteRepository.findById(loginDtoRequest.getCi()).get();
        String token = jwtService.generateToken(loginDtoRequest.getCi());
        Map data = ClienteDtoResponse.responseDataCreateCliente(cliente);
        data.put("token",token);
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("cliente", data));
    }
}
