package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoRequest;
import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoResponse;
import com.caixa_rapido.services.CategoriaProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaProdutoController {

    private final CategoriaProdutoService service;

    @PostMapping
    public ResponseEntity<CategoriaProdutoResponse> cadastrar(
            @Valid @RequestBody CategoriaProdutoRequest dto
    ) {
        return status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoResponse>> getCategorias() {
        var categorias = service.getAllResponse();

        return categorias.isEmpty()
                ? noContent().build()
                : ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoResponse> getCategoriaPorId(@PathVariable UUID id) {
        return ok(service.getResponsePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdutoResponse> alterar(
            @PathVariable UUID id,
            @Valid @RequestBody CategoriaProdutoRequest dto
    ) {
        return ok(service.alterar(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
