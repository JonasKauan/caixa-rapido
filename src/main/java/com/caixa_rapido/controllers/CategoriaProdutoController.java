package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoRequest;
import com.caixa_rapido.models.CategoriaProduto;
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
    public ResponseEntity<CategoriaProduto> cadastrar(
            @Valid @RequestBody CategoriaProdutoRequest dto
    ) {
        return status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProduto>> getCategorias() {
        var categorias = service.getAllResponse();

        return categorias.isEmpty()
                ? noContent().build()
                : ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProduto> getCategoriaPorId(@PathVariable UUID id) {
        return ok(service.getPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProduto> alterar(
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
