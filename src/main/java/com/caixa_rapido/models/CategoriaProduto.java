package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria_produto")
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCategoriaProduto;

    private String nome;
    private Integer desconto;

    public CategoriaProduto(String nome) {
        this.nome = nome;
    }
}
