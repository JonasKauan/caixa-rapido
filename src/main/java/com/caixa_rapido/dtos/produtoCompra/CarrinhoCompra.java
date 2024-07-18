package com.caixa_rapido.dtos.produtoCompra;

import java.util.List;

public record CarrinhoCompra(
   List<ProdutoCompraCarrinho> produtosCompra,
   double total,
   int totalEmPontos
) {}
