package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.parcela.ParcelaResponse;
import com.caixa_rapido.models.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, UUID> {

    @Query("SELECT p FROM Parcela p")
    List<ParcelaResponse> findAllResponse();
}
