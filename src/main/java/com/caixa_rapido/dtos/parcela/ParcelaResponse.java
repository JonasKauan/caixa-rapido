package com.caixa_rapido.dtos.parcela;

import com.caixa_rapido.dtos.compra.CompraResponse;
import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoResponse;
import com.caixa_rapido.models.FormaPagamento;
import com.caixa_rapido.models.Parcela;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ParcelaResponse {
    private final UUID idParcela;
    private final FormaPagamentoResponse formaPagamento;
    private final CompraResponse compra;
    private final Double valor;

    public ParcelaResponse(Parcela parcela) {
        this.idParcela = parcela.getIdParcela();
        this.formaPagamento = new FormaPagamentoResponse(
                parcela.getFormaPagamento() != null
                    ? parcela.getFormaPagamento()
                    : new FormaPagamento("Pontos")
        );
        this.compra = new CompraResponse(parcela.getCompra());
        this.valor = parcela.getValor();
    }
}
