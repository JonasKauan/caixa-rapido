package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoResponse;
import com.caixa_rapido.models.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, UUID> {

    @Query("SELECT c FROM CategoriaProduto c")
    List<CategoriaProdutoResponse> findAllResponse();
}
