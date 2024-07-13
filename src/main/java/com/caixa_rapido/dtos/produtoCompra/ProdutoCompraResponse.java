package com.caixa_rapido.dtos.produtoCompra;

import com.caixa_rapido.dtos.compra.CompraResponse;
import com.caixa_rapido.dtos.produto.ProdutoResponse;
import com.caixa_rapido.models.ProdutoCompra;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProdutoCompraResponse {
    private final UUID idProdutoCompra;
    private final CompraResponse compra;
    private final ProdutoResponse produto;
    private final Integer quantidade;
    private final Double total;

    public ProdutoCompraResponse(ProdutoCompra produtoCompra) {
        this.idProdutoCompra = produtoCompra.getIdProdutoCompra();
        this.compra = new CompraResponse(produtoCompra.getCompra());
        this.produto = new ProdutoResponse(produtoCompra.getProduto());
        this.quantidade = produtoCompra.getQuantidade();
        this.total = produtoCompra.getTotal();
    }
}
