package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCompra;

    @ManyToOne
    @JoinColumn(name = "fkcliente")
    private Cliente cliente;
    private double total;
    private LocalDate data;

    @OneToMany(mappedBy = "compra")
    private List<ProdutoCompra> produtosCompra;

    public Compra() {
        this.data = LocalDate.now();
    }

    public Compra(UUID idCompra, Cliente cliente, double total, LocalDate data) {
        this.idCompra = idCompra;
        this.cliente = cliente;
        this.total = total;
        this.data = data;
    }

    public void calcularTotal() {
        for(ProdutoCompra produtoCompra : produtosCompra) {
            total += produtoCompra.getTotal();
        }
    }
}
