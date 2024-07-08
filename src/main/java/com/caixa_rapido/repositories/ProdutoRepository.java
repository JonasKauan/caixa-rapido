package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.produto.ProdutoResponse;
import com.caixa_rapido.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("""
            SELECT new Produto(
                p.idProduto,
                p.nome,
                p.valor,
                new CategoriaProduto(p.categoria.nome)
            )
            FROM Produto p
            """)
    List<ProdutoResponse> findAllResponse();
}
