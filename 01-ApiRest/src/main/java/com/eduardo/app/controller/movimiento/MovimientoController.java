package com.eduardo.app.controller.movimiento;

import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.ErrorResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.MovimientoDepositoDtoRequest;
import com.eduardo.app.dto.request.MovimientoRetiroDtoRequest;
import com.eduardo.app.dto.response.MovimientoDtoResponse;
import com.eduardo.app.entity.CuentaEntity;
import com.eduardo.app.entity.MovimientoEntity;
import com.eduardo.app.entity.TipoMovimientoEntity;
import com.eduardo.app.repository.tipoMovimiento.TipoMovimientoRepository;
import com.eduardo.app.repository.cuenta.CuentaRepository;
import com.eduardo.app.repository.movimiento.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = RestRequestMapping.MOTION)
public class MovimientoController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SuccesResponse succesResponse;
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;

    @PostMapping(path = "/deposito")
    public ResponseEntity<ApiResponse> createDeposito(@Valid @RequestBody MovimientoDepositoDtoRequest movimientoDepositoDtoRequest) {
        Optional<CuentaEntity> cuentaFind = cuentaRepository.findById(movimientoDepositoDtoRequest.getNroAccount());
        if (cuentaFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse.createErrorResponse("Datos Invalidos !!!"));
        }
        CuentaEntity cuenta = cuentaFind.get();
        TipoMovimientoEntity tipoDeposito = tipoMovimientoRepository.findByName("Deposito");
        MovimientoEntity movimiento = MovimientoEntity.builder().fecha(new Date()).cuenta(cuenta).tipoMovimiento(tipoDeposito).monto(movimientoDepositoDtoRequest.getMonto()).build();
        movimientoRepository.save(movimiento);
        Map data = MovimientoDtoResponse.responseDataMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(succesResponse.createSuccessResponse("movimiento", data));
    }

    @PostMapping(path = "/retiro")
    public ResponseEntity<ApiResponse> createRetiro(@Valid @RequestBody MovimientoRetiroDtoRequest movimientoRetiroDtoRequest) {
        Optional<CuentaEntity> cuentaFind = cuentaRepository.findById(movimientoRetiroDtoRequest.getNroAccount());
        if(cuentaFind.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse.createErrorResponse("Datos Invalidos !!!"));
        }
        CuentaEntity cuenta = cuentaRepository.findById(movimientoRetiroDtoRequest.getNroAccount()).get();
        if(!passwordEncoder.matches(movimientoRetiroDtoRequest.getPasswordAccount(),cuenta.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse.createErrorResponse("Datos Invalidos !!!"));
        }
        if (cuenta.getSaldo() < movimientoRetiroDtoRequest.getMonto()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse.createErrorResponse("Saldo insuficiente !!!"));
        }
        TipoMovimientoEntity tipoDeposito = tipoMovimientoRepository.findByName("Retiro");
        MovimientoEntity movimiento = MovimientoEntity.builder().fecha(new Date()).cuenta(cuenta).tipoMovimiento(tipoDeposito).monto(movimientoRetiroDtoRequest.getMonto()).build();
        movimientoRepository.save(movimiento);
        Map data = MovimientoDtoResponse.responseDataMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(succesResponse.createSuccessResponse("movimiento", data));
    }


}
