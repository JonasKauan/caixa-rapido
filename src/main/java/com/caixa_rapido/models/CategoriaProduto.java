package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categoria_produto")
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCategoriaProduto;

    private String nome;
    private Integer desconto;
}
