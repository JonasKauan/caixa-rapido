package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.compra.CompraPutRequest;
import com.caixa_rapido.dtos.compra.CompraResponse;
import com.caixa_rapido.services.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService service;

    @PostMapping
    public ResponseEntity<CompraResponse> cadastrar(
            @RequestParam(required = false) UUID fkCliente
    ) {
        return status(HttpStatus.CREATED).body(new CompraResponse(service.cadastrar(fkCliente)));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponse>> getCompras() {
        var compras = service.getAllResponse();

        return compras.isEmpty()
                ? noContent().build()
                : ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> getCompraPorId(@PathVariable UUID id) {
        return ok(new CompraResponse(service.getPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponse> alterar(
            @PathVariable UUID id,
            @RequestBody @Valid CompraPutRequest dto
    ) {
        return ok(new CompraResponse(service.alterar(id, dto)));
    }

    @PutMapping("/finalizar-compra/{id}")
    public ResponseEntity<CompraResponse> finalizarCompra(@PathVariable UUID id) {
        return ok(new CompraResponse(service.finalizarCompra(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletaPorId(id);
        return noContent().build();
    }
}
