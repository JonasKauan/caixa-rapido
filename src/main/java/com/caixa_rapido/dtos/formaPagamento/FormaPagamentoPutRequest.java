package com.caixa_rapido.dtos.formaPagamento;

import jakarta.validation.constraints.PositiveOrZero;

public record FormaPagamentoPutRequest(
        String nome,
        @PositiveOrZero
        Integer desconto
) {}
