package com.caixa_rapido.dtos.produtoCompra;

import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProdutoCompraPutRequest(
        UUID fkCompra,
        UUID fkProduto,
        @Positive
        int quantidade
) {}
