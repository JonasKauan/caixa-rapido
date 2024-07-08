package com.caixa_rapido.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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

    private Double total;
    private LocalDate data;
}
