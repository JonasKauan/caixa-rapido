package com.caixa_rapido.dtos.compra;

import com.caixa_rapido.dtos.cliente.ClienteResponse;
import com.caixa_rapido.models.Compra;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompraResponse {
    private final UUID idCompra;
    private final String finalizada;
    private final Double total;
    private final LocalDate data;
    private final Integer totalEmPontos;
    private final ClienteResponse cliente;

    public CompraResponse(Compra compra) {
        this.idCompra = compra.getIdCompra();
        this.finalizada = compra.isFinalizada() ? "Sim" : "Não";
        this.total = compra.getTotal();
        this.data = compra.getData();
        this.totalEmPontos = compra.getTotalEmPontos();
        this.cliente = compra.getCliente() != null ? new ClienteResponse(compra.getCliente()) : null;
    }
}
