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
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;
    private String nome;
    private String cpf;
    private String email;
    private int pontos;

    public Cliente(UUID idCliente, String nome, int pontos) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.pontos = pontos;
    }
}
