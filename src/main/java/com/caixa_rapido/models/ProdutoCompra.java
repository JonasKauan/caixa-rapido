package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
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
        this.total = this.produto.getValorComDesconto() * quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoCompra that)) return false;
        return Objects.equals(idProdutoCompra, that.idProdutoCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdutoCompra);
    }
}
