package com.eduardo.app.controller.cliente;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.ErrorResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.ClienteDtoRequest;
import com.eduardo.app.dto.response.ClienteDtoResponse;
import com.eduardo.app.entity.ClienteEntity;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = RestRequestMapping.CLIENT)
public class ClienteController {
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private SuccesResponse succesResponse;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody ClienteDtoRequest clienteDtoRequest) {
        Optional<ClienteEntity> clientFind = clienteRepository.findById(clienteDtoRequest.getCi());
        if (clientFind.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse.createErrorResponse("El ci ya existe", Collections.emptyList()));
        }
        ClienteEntity cliente = new ClienteEntity();
        cliente.setCi(clienteDtoRequest.getCi());
        cliente.setNombre(clienteDtoRequest.getNombre());
        cliente.setApellido(clienteDtoRequest.getApellido());
        clienteRepository.save(cliente);
        Map data = ClienteDtoResponse.responseDataCreateCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(succesResponse.createSuccessResponse("cliente", data));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody ClienteDtoRequest clienteDtoRequest) {
        Optional<ClienteEntity> clientFind = clienteRepository.findById(clienteDtoRequest.getCi());
        if (clientFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.createErrorResponse("El Cliente no se encontro", Collections.emptyList()));
        }
        clientFind.get().setNombre(clienteDtoRequest.getNombre());
        clientFind.get().setApellido(clienteDtoRequest.getApellido());
        clienteRepository.save(clientFind.get());
        Map data = ClienteDtoResponse.responseDataUpdateCliente(clientFind.get());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("cliente", data));
    }

    @GetMapping("/cuentas/{ci}")
    public ResponseEntity<ApiResponse> obtenerCuentasOfCliente(@PathVariable String ci) {
        Optional<ClienteEntity> clienteFind = clienteRepository.findById(ci);
        if (clienteFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.createErrorResponse("El Cliente no se encontro", Collections.emptyList()));
        }
        List<Map> data = ClienteDtoResponse.responseDataCuentaCliente(clienteFind.get().getCuentas());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("cuentas", data));
    }

}
