package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.compra.CompraResponse;
import com.caixa_rapido.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompraRepository extends JpaRepository<Compra, UUID> {

    @Query("SELECT c FROM Compra c")
    List<CompraResponse> findAllResponse();
}
