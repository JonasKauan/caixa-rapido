package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.parcela.ParcelaRequest;
import com.caixa_rapido.dtos.parcela.ParcelaResponse;
import com.caixa_rapido.services.ParcelaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/parcelas")
@RequiredArgsConstructor
public class ParcelaController {

    private final ParcelaService service;

    @PostMapping
    public ResponseEntity<ParcelaResponse> cadastrar(@RequestBody @Valid ParcelaRequest dto) {
        return status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ParcelaResponse>> getParcelas() {
        var parcelas = service.getParcelasResponse();

        return parcelas.isEmpty()
                ? noContent().build()
                : ok(parcelas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelaResponse> getParcelaPorId(@PathVariable UUID id) {
        return ok(service.getResponsePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelaResponse> alterar(
        @PathVariable UUID id,
        @RequestBody @Valid ParcelaRequest dto
    ) {
        return ok(service.alterar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
