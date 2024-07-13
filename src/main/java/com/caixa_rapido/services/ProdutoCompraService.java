package com.caixa_rapido.services;

import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPostRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPutRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraResponse;
import com.caixa_rapido.models.ProdutoCompra;
import com.caixa_rapido.repositories.ProdutoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoCompraService {
    private final ProdutoCompraRepository repository;
    private final CompraService compraService;
    private final ProdutoService produtoService;

    public ProdutoCompraResponse cadastrar(ProdutoCompraPostRequest dto) {
        var produtoCompra = new ProdutoCompra();

        produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));
        produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));
        produtoCompra.setQuantidade(dto.quantidade());

        var compra = produtoCompra.getCompra();

        for(var p : compra.getProdutosCompra()) {
            if(p.getProduto().equals(produtoCompra.getProduto())) {
                produtoCompra = p;
                produtoCompra.setQuantidade(dto.quantidade() + p.getQuantidade());
                break;
            }
        }

        produtoCompra.setTotal();

        return new ProdutoCompraResponse(repository.save(produtoCompra));
    }

    public List<ProdutoCompraResponse> getAllResponse() {
        return repository.findAllResponse();
    }

    public ProdutoCompra getPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Item da compra com o id listado não existe"
            );

        return repository.findById(id).get();
    }

    public ProdutoCompraResponse getResponsePorId(UUID id) {
        return new ProdutoCompraResponse(getPorId(id));
    }

    public ProdutoCompraResponse alterar(UUID id, ProdutoCompraPutRequest dto) {
        var produtoCompra = getPorId(id);

        if(dto.fkProduto() != null) produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));
        if(dto.fkCompra() != null) produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));

        produtoCompra.setQuantidade(dto.quantidade());
        produtoCompra.setTotal();

        return new ProdutoCompraResponse(repository.save(produtoCompra));
    }

    public void deletarPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Item da compra com o id listado não existe"
            );

        repository.deleteById(id);
    }
}
