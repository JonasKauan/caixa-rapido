package com.caixa_rapido.services;

import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoRequest;
import com.caixa_rapido.dtos.categoriaProduto.CategoriaProdutoResponse;
import com.caixa_rapido.models.CategoriaProduto;
import com.caixa_rapido.repositories.CategoriaProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository repository;

    public CategoriaProdutoResponse cadastrar(CategoriaProdutoRequest dto) {
        var categoria = new CategoriaProduto();
        BeanUtils.copyProperties(dto, categoria);

        return new CategoriaProdutoResponse(repository.save(categoria));
    }

    public List<CategoriaProdutoResponse> getAllResponse() {
        return repository.findAllResponse();
    }

    public CategoriaProdutoResponse getResponsePorId(UUID id) {
        return new CategoriaProdutoResponse(getPorId(id));
    }

    public CategoriaProduto getPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Categoria com o id listado não foi encontrada"
            );

        return repository.findById(id).get();
    }

    public CategoriaProdutoResponse alterar(CategoriaProdutoRequest dto, UUID id) {
        var categoria = getPorId(id);
        BeanUtils.copyProperties(dto, categoria);
        return new CategoriaProdutoResponse(repository.save(categoria));
    }

    public void deletarPorId(UUID id) {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}
