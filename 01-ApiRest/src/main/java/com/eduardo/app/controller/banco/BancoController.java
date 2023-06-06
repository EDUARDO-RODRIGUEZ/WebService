package com.eduardo.app.controller.banco;


import com.eduardo.app.component.ApiResponse;
import com.eduardo.app.component.ErrorResponse;
import com.eduardo.app.component.SuccesResponse;
import com.eduardo.app.constant.RestRequestMapping;
import com.eduardo.app.dto.request.BancoDtoRequest;
import com.eduardo.app.dto.response.BancoDtoResponse;
import com.eduardo.app.entity.BancoEntity;
import com.eduardo.app.repository.banco.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = RestRequestMapping.BANK)
public class BancoController {
    @Autowired
    private SuccesResponse succesResponse;
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private BancoRepository bancoRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody BancoDtoRequest bancoDtoRequest) {
        BancoEntity banco = new BancoEntity();
        banco.setNombre(bancoDtoRequest.getNombre());
        bancoRepository.save(banco);
        Map data = BancoDtoResponse.responseDataCreateBanco(banco);
        return ResponseEntity.status(HttpStatus.CREATED).body(succesResponse.createSuccessResponse("banco", data));
    }

    @DeleteMapping("/{bancoId}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long bancoId) {
        Optional<BancoEntity> bancoFind = bancoRepository.findById(bancoId);
        if (bancoFind.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.createErrorResponse("El id del banco no encontro!!!", null));
        }
        Map data = BancoDtoResponse.responseDataRemoveBanco(bancoFind.get());
        bancoRepository.delete(bancoFind.get());
        return ResponseEntity.status(HttpStatus.OK).body(succesResponse.createSuccessResponse("banco", data));
    }
}
