package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraResponse;
import com.caixa_rapido.models.ProdutoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<ProdutoCompra, UUID> {

    @Query("""
            SELECT new ProdutoCompra(
                p.idProdutoCompra,
                p.compra,
                new Produto(
                    p.produto.idProduto,
                    p.produto.nome,
                    p.produto.valor,
                    p.produto.categoria
                ),
                p.quantidade,
                p.total
            )
            FROM ProdutoCompra p
            """)
    List<ProdutoCompraResponse> findAllResponse();
}
