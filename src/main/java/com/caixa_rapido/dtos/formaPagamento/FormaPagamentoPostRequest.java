package com.caixa_rapido.dtos.formaPagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record FormaPagamentoPostRequest(

        @NotBlank
        String nome,

        @PositiveOrZero
        int desconto
) {}
