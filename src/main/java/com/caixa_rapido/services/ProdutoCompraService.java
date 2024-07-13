package com.caixa_rapido.services;

import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPostRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraPutRequest;
import com.caixa_rapido.dtos.produtoCompra.ProdutoCompraResponse;
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
    private final ProdutoCompraRepository produtoCompraRepository;
    private final CompraService compraService;
    private final ProdutoService produtoService;

    public ProdutoCompra cadastrar(ProdutoCompraPostRequest dto) {
        var produtoCompra = new ProdutoCompra();

        produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));
        produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));

        produtoCompra.setQuantidade(dto.quantidade());
        produtoCompra.setTotal();

        return produtoCompraRepository.save(produtoCompra);
    }

    public List<ProdutoCompraResponse> getAllResponse() {
        return produtoCompraRepository.findAllResponse();
    }

    public ProdutoCompra getPorId(UUID id) {
        if(!produtoCompraRepository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Item da compra com o id listado não existe"
            );

        return produtoCompraRepository.findById(id).get();
    }

    public ProdutoCompra alterar(UUID id, ProdutoCompraPutRequest dto) {
        var produtoCompra = getPorId(id);

        if(dto.fkProduto() != null) produtoCompra.setProduto(produtoService.getPorId(dto.fkProduto()));
        if(dto.fkCompra() != null) produtoCompra.setCompra(compraService.getPorId(dto.fkCompra()));

        produtoCompra.setQuantidade(dto.quantidade());
        produtoCompra.setTotal();

        return produtoCompraRepository.save(produtoCompra);
    }

    public void deletarPorId(UUID id) {
        if(!produtoCompraRepository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Item da compra com o id listado não existe"
            );

        produtoCompraRepository.deleteById(id);
    }
}
