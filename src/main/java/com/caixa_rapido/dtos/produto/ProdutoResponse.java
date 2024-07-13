package com.caixa_rapido.dtos.produto;

import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoResponse;
import com.caixa_rapido.models.Produto;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProdutoResponse {
    private final UUID idProduto;
    private final String nome;
    private final Double valor;
    private final Double valorComDesconto;
    private final CategoriaProdutoResponse categoria;

    public ProdutoResponse(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.valorComDesconto = produto.getValorComDesconto();
        this.categoria = new CategoriaProdutoResponse(produto.getCategoria());
    }
}
