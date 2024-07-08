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
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;

    private String nome;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "fkcategoria")
    private CategoriaProduto categoria;
}
