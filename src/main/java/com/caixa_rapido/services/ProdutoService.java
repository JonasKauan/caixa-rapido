package com.caixa_rapido.services;

import com.caixa_rapido.dtos.produto.ProdutoRequest;
import com.caixa_rapido.dtos.produto.ProdutoResponse;
import com.caixa_rapido.models.Produto;
import com.caixa_rapido.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final CategoriaProdutoService categoriaService;


    public ProdutoResponse cadastrar(ProdutoRequest dto) {
        var produto = new Produto();
        produto.setCategoria(categoriaService.getPorId(dto.fkCategoria()));
        BeanUtils.copyProperties(dto, produto);
        return new ProdutoResponse(repository.save(produto));
    }

    public List<ProdutoResponse> getAllResponse() {
        return repository.findAllResponse();
    }

    public Produto getPorId(UUID id) {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return repository.findById(id).get();
    }

    public ProdutoResponse getResponsePorId(UUID id) {
        return new ProdutoResponse(getPorId(id));
    }

    public ProdutoResponse alterar(ProdutoRequest dto, UUID id) {
        var produto = getPorId(id);
        produto.setCategoria(categoriaService.getPorId(dto.fkCategoria()));
        BeanUtils.copyProperties(dto, produto);
        return new ProdutoResponse(repository.save(produto));
    }

    public void deletarPorId(UUID id) {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}
