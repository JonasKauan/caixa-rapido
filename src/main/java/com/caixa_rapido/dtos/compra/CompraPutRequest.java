package com.caixa_rapido.dtos.compra;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record CompraPutRequest(
        UUID fkCliente,
        double total,
        @PastOrPresent
        LocalDate data
) {}
