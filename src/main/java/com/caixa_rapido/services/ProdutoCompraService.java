package com.caixa_rapido.services;

import com.caixa_rapido.dtos.produtoCompra.*;
import com.caixa_rapido.models.ProdutoCompra;
import com.caixa_rapido.repositories.ProdutoCompraRepository;
import lombok.RequiredArgsConstructor;
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

        produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));

        if(produtoCompra.getCompra().isFinalizada())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa compra já foi finalizada");

        produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));
        produtoCompra = agregarQuantidadeCompra(produtoCompra, dto.quantidade());
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

        if(dto.fkCompra() != null) {
            produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));

            if(produtoCompra.getCompra().isFinalizada())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa compra já foi finalizada");
        }

        if(dto.fkProduto() != null) produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));

        produtoCompra = agregarQuantidadeCompra(produtoCompra, dto.quantidade());
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

    public CarrinhoCompra getCarrinho(UUID idCompra) {
        var produtosCompra = repository.findAllCarrinhoResponse(compraService.getPorId(idCompra));

        return new CarrinhoCompra(
            produtosCompra,
            produtosCompra.stream().mapToDouble(ProdutoCompraCarrinho::getTotal).sum()
        );
    }

    private ProdutoCompra agregarQuantidadeCompra(ProdutoCompra produtoCompra, int quantidade) {
        produtoCompra.setQuantidade(quantidade);

        var compra = produtoCompra.getCompra();

        for(var p : compra.getProdutosCompra()) {
            if(p.equals(produtoCompra)) continue;

            if(p.getProduto().equals(produtoCompra.getProduto())) {
                p.setQuantidade(quantidade + p.getQuantidade());
                return p;
            }
        }

        return produtoCompra;
    }
}
