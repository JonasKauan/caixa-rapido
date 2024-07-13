package com.caixa_rapido.services;

import com.caixa_rapido.dtos.parcela.ParcelaRequest;
import com.caixa_rapido.dtos.parcela.ParcelaResponse;
import com.caixa_rapido.models.Parcela;
import com.caixa_rapido.repositories.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelaService {
    private final ParcelaRepository repository;
    private final CompraService compraService;
    private final FormaPagamentoService formaPagamentoService;

    public ParcelaResponse cadastrar(ParcelaRequest dto) {
        var parcela = new Parcela();

        parcela.setCompra(compraService.getPorId(dto.fkCompra()));
        parcela.setFormaPagamento(formaPagamentoService.getPorId(dto.fkFormaPagamento()));

        BeanUtils.copyProperties(dto, parcela);

        return new ParcelaResponse(repository.save(parcela));
    }

    public List<ParcelaResponse> getParcelasResponse() {
        return repository.findAllResponse();
    }

    public Parcela getPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parcela com o id listado não existe");

        return repository.findById(id).get();
    }

    public ParcelaResponse getResponsePorId(UUID id) {
        return new ParcelaResponse(getPorId(id));
    }

    public ParcelaResponse alterar(UUID id, ParcelaRequest dto) {
        var parcela = getPorId(id);

        parcela.setCompra(compraService.getPorId(dto.fkCompra()));
        parcela.setFormaPagamento(formaPagamentoService.getPorId(dto.fkFormaPagamento()));

        BeanUtils.copyProperties(dto, parcela);

        return new ParcelaResponse(repository.save(parcela));
    }

    public void deletarPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parcela com o id listado não existe");

        repository.deleteById(id);
    }
}
