package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "produto_compra")
public class ProdutoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProdutoCompra;

    @ManyToOne
    @JoinColumn(name = "fkcompra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "fkproduto")
    private Produto produto;

    private Integer quantidade;
    private Double total;
}
