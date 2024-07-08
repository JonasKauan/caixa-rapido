package com.caixa_rapido.dtos.cliente;

import com.caixa_rapido.models.Cliente;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ClienteResponse {
    private final UUID idCliente;
    private final String nome;
    private final Integer pontos;

    public ClienteResponse(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.nome = cliente.getNome();
        this.pontos = cliente.getPontos();
    }
}
