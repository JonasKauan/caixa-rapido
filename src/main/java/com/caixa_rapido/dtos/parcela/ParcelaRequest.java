package com.caixa_rapido.dtos.parcela;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;


public record ParcelaRequest(
        UUID fkFormaPagamento,
        @NotNull
        UUID fkCompra,
        @NotNull
        @Positive
        Double valor
) {}
