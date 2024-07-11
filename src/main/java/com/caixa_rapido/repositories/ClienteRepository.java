package com.caixa_rapido.repositories;

import com.caixa_rapido.dtos.cliente.ClienteResponse;
import com.caixa_rapido.models.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("""
            SELECT new Cliente(
                c.idCliente,
                c.nome,
                c.pontos
            )
            FROM Cliente c
            """)
    List<ClienteResponse> findAllResponse();

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
