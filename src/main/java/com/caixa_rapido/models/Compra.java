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
    private boolean finalizada;

    @OneToMany(mappedBy = "compra")
    private List<ProdutoCompra> produtosCompra;

    @OneToMany(mappedBy = "compra")
    private List<Parcela> parcelas;

    public Compra() {
        this.data = LocalDate.now();
    }

    public Compra(UUID idCompra, Cliente cliente, double total, LocalDate data) {
        this.idCompra = idCompra;
        this.cliente = cliente;
        this.total = total;
        this.data = data;
    }

    public double calcularTotalParcelas() {
        return parcelas.stream()
                .filter(parcela -> parcela.getFormaPagamento() != null)
                .mapToDouble(Parcela::getValor)
                .sum();
    }

    public int calcularPontosGastos() {
        return (int) parcelas.stream()
                .filter(parcela -> parcela.getFormaPagamento() == null)
                .mapToDouble(Parcela::getValor)
                .sum();
    }

    public void setTotal() {
        total = produtosCompra.stream()
            .mapToDouble(ProdutoCompra::getTotal)
            .sum();
    }

    public double getTotalComDesconto() {
        if(parcelas == null) return total;

        var parcelaComDesconto = parcelas.stream()
                .filter(parcela -> parcela.getFormaPagamento().getDesconto() > 0)
                .findFirst();

        int desconto = parcelaComDesconto.isEmpty()
                ? 0
                : parcelaComDesconto.get().getFormaPagamento().getDesconto();

        return total * (1 - desconto / 100.);
    }

    public int getTotalEmPontos() {
        return (int) getTotalComDesconto() * 10;
    }
}
