package com.caixa_rapido.services;

import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoPostRequest;
import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoPutRequest;
import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoResponse;
import com.caixa_rapido.models.FormaPagamento;
import com.caixa_rapido.repositories.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {
    private final FormaPagamentoRepository repository;

    public FormaPagamentoResponse cadastrar(FormaPagamentoPostRequest dto) {
        var formaPagamento = new FormaPagamento();
        BeanUtils.copyProperties(dto, formaPagamento);
        return new FormaPagamentoResponse(repository.save(formaPagamento));
    }

    public List<FormaPagamentoResponse> getFormasPagamento() {
        return repository.findAllResponse();
    }

    public FormaPagamento getPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Forma de pagamento com o id listado não existe"
            );

        return repository.findById(id).get();
    }

    public FormaPagamentoResponse getResponsePorId(UUID id) {
        return new FormaPagamentoResponse(getPorId(id));
    }

    public FormaPagamentoResponse alterar(UUID id, FormaPagamentoPutRequest dto) {
        var formaPagamento = getPorId(id);
        if(dto.nome() != null) formaPagamento.setNome(dto.nome());
        if(dto.desconto() != null) formaPagamento.setDesconto(dto.desconto());
        return new FormaPagamentoResponse(repository.save(formaPagamento));
    }

    public void deletarPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Forma de pagamento com o id listado não existe"
            );

        repository.deleteById(id);
    }
}
