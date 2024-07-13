package com.caixa_rapido.dtos.categoriaProduto;

import com.caixa_rapido.models.CategoriaProduto;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoriaProdutoResponse {
    private final UUID idCategoriaProduto;
    private final String nome;
    private final Integer desconto;

    public CategoriaProdutoResponse(CategoriaProduto categoria) {
        this.idCategoriaProduto = categoria.getIdCategoriaProduto();
        this.nome = categoria.getNome();
        this.desconto = categoria.getDesconto();
    }
}
