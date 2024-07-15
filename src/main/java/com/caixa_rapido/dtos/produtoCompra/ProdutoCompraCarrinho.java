package com.caixa_rapido.dtos.produtoCompra;

import com.caixa_rapido.dtos.produto.ProdutoResponse;
import com.caixa_rapido.models.ProdutoCompra;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProdutoCompraCarrinho {
    private final UUID idProdutoCompra;
    private final ProdutoResponse produto;
    private final int quantidade;
    private final double total;

    public ProdutoCompraCarrinho(ProdutoCompra produtoCompra) {
        this.idProdutoCompra = produtoCompra.getIdProdutoCompra();
        this.produto = new ProdutoResponse(produtoCompra.getProduto());
        this.quantidade = produtoCompra.getQuantidade();
        this.total = produtoCompra.getTotal();
    }
}
