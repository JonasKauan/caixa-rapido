package com.caixa_rapido.dtos.produtoCompra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProdutoCompraPostRequest(
        @NotNull
        UUID fkCompra,
        @NotNull
        UUID fkProduto,
        @Positive
        int quantidade
) {}
