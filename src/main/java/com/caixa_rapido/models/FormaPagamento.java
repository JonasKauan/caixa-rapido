package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFormaPagamento;

    private String nome;
    private Integer desconto;

    public FormaPagamento(String nome) {
        this.nome = nome;
    }
}
