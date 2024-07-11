package com.caixa_rapido.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClientePostRequest(
        @NotBlank
        String nome,

        @CPF
        @NotBlank
        String cpf,

        @NotBlank
        @Email
        String email
) {}
