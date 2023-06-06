package com.eduardo.app.controller.cuenta;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.ErrorResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.CuentaDtoRequest;
import com.eduardo.app.dto.response.CuentaDtoResponse;
import com.eduardo.app.entity.BancoEntity;
import com.eduardo.app.entity.ClienteEntity;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.entity.TipoCuentaEntity;
import com.eduardo.app.repository.banco.BancoRepository;
import com.eduardo.app.repository.cliente.ClienteRepository;
import com.eduardo.app.repository.cuenta.CuentaRepository;
import com.eduardo.app.repository.tipoCuenta.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = RestRequestMapping.ACCOUNT)
public class CuentaController {

    @Autowired
    private SuccesResponse succesResponse;
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/{nroAccount}")
    public ResponseEntity<ApiResponse> getCuenta(@PathVariable String nroAccount) {
        Optional<CuentaEntity> cuentaFind = cuentaRepository.findById(nroAccount);
        if (cuentaFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.createErrorResponse("Cuenta no encontrada !!!", Collections.emptyList()));
        }
        Map data = CuentaDtoResponse.responseDataCuenta(cuentaFind.get());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("cuenta", data));
    }

    @GetMapping(path = "/movimiento/{nroAccount}")
    public ResponseEntity<ApiResponse> obtenerMovimientoOfCuenta(@PathVariable String nroAccount) {
        Optional<CuentaEntity> cuentaFind = cuentaRepository.findById(nroAccount);
        if (cuentaFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.createErrorResponse("Cuenta no encontrada !!!", Collections.emptyList()));
        }
        List<Map> data = CuentaDtoResponse.responseDataMovimientos(cuentaFind.get().getMovimientos());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("movimientos", data));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CuentaDtoRequest cuentaDtoRequest) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(cuentaDtoRequest.getCi());
        Optional<BancoEntity> banco = bancoRepository.findById(cuentaDtoRequest.getBancoId());
        Optional<CuentaEntity> cuentaFind = cuentaRepository.findById(cuentaDtoRequest.getNro());
        Optional<TipoCuentaEntity> tipoCuenta = tipoCuentaRepository.findById(cuentaDtoRequest.getTipoCuentaId());
        if (cliente.isEmpty() || banco.isEmpty() || tipoCuenta.isEmpty() || cuentaFind.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse.createErrorResponse("Datos no Validos !!!"));
        }
        CuentaEntity cuenta = CuentaEntity.builder().nro(cuentaDtoRequest.getNro()).password(passwordEncoder.encode(cuentaDtoRequest.getPassword()))
                .tipoCuenta(tipoCuenta.get()).banco(banco.get()).cliente(cliente.get()).saldo(0.0).build();
        cuentaRepository.save(cuenta);
        Map data = CuentaDtoResponse.responseDataCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(succesResponse.createSuccessResponse("cuenta", data));
    }
}
