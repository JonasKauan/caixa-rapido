package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.produtoCompra.CarrinhoCompra;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPostRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPutRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraResponse;
import com.caixa_rapido.services.ProdutoCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/produtos-compra")
@RequiredArgsConstructor
public class ProdutoCompraController {
    private final ProdutoCompraService service;

    @PostMapping
    public ResponseEntity<ProdutoCompraResponse> cadastrar(
            @RequestBody @Valid ProdutoCompraPostRequest dto
    ) {
        return status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoCompraResponse>> getProdutosCompra() {
        var produtosCompra = service.getAllResponse();

        return produtosCompra.isEmpty()
                ? noContent().build()
                : ok(produtosCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoCompraResponse> getProdutoCompraPorId(@PathVariable UUID id) {
        return ok(service.getResponsePorId(id));
    }

    @GetMapping("/carrinho-compra/{idCompra}")
    public ResponseEntity<CarrinhoCompra> getCarrinhoCompra(@PathVariable UUID idCompra) {
        return ok(service.getCarrinho(idCompra));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCompraResponse> alterar(
            @PathVariable UUID id,
            @RequestBody @Valid ProdutoCompraPutRequest dto
    ) {
        return ok(service.alterar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
