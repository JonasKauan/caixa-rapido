package com.caixa_rapido.dtos.cliente;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record ClientePutRequest(
        String nome,

        @CPF
        String cpf,

        @Email
        String email,

        int pontos
) {}
