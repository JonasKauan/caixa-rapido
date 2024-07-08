package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.produto.ProdutoRequest;
import com.caixa_rapido.dtos.produto.ProdutoResponse;
import com.caixa_rapido.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest dto) {
        return status(HttpStatus.CREATED).body(new ProdutoResponse(service.cadastrar(dto)));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAll() {
        var produtos = service.getAllResponse();

        return produtos.isEmpty()
                ? noContent().build()
                : ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getPorId(@PathVariable UUID id) {
        return ok(new ProdutoResponse(service.getPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> alterar(
            @PathVariable UUID id,
            @Valid @RequestBody ProdutoRequest dto
    ) {
        return ok(new ProdutoResponse(service.alterar(dto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
