package com.caixa_rapido.dtos.categoriaProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CategoriaProdutoRequest(
    @NotBlank
    String nome,

    @PositiveOrZero
    int desconto
) {}
