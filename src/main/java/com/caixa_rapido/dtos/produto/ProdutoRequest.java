package com.caixa_rapido.dtos.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProdutoRequest(
        @NotBlank
        String nome,
        @NotNull
        @Positive
        Double valor,
        @NotNull
        UUID fkCategoria
) {}
