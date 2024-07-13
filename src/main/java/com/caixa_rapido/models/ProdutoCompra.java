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

    public ProdutoCompra(
            UUID idProdutoCompra,
            Compra compra,
            Produto produto,
            Integer quantidade,
            Double total
    ) {
        this.idProdutoCompra = idProdutoCompra;
        this.compra = compra;
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = total;
    }

    public void setTotal() {
        double desconto = this.produto.getCategoria().getDesconto();
        this.total = (this.produto.getValor() * (1 - desconto / 100)) * quantidade;
    }
}
