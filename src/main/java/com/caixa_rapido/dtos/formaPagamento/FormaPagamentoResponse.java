package com.caixa_rapido.dtos.formaPagamento;

import com.caixa_rapido.models.FormaPagamento;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FormaPagamentoResponse {
    private final UUID idFormaPagamento;
    private final String nome;
    private final Integer desconto;

    public FormaPagamentoResponse(FormaPagamento formaPagamento) {
        this.idFormaPagamento = formaPagamento.getIdFormaPagamento();
        this.nome = formaPagamento.getNome();
        this.desconto = formaPagamento.getDesconto();
    }
}
