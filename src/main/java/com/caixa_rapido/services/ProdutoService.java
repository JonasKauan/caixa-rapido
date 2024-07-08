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
    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoService categoriaService;


    public Produto cadastrar(ProdutoRequest dto) {
        var produto = new Produto();
        produto.setCategoria(categoriaService.getPorId(dto.fkCategoria()));
        BeanUtils.copyProperties(dto, produto);
        return produtoRepository.save(produto);
    }

    public List<ProdutoResponse> getAllResponse() {
        return produtoRepository.findAllResponse();
    }

    public Produto getPorId(UUID id) {
        if(!produtoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return produtoRepository.findById(id).get();
    }

    public Produto alterar(ProdutoRequest dto, UUID id) {
        var produto = getPorId(id);
        produto.setCategoria(categoriaService.getPorId(dto.fkCategoria()));
        BeanUtils.copyProperties(dto, produto);
        return produtoRepository.save(produto);
    }

    public void deletarPorId(UUID id) {
        if(!produtoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }
}
