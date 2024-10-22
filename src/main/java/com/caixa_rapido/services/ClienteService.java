package com.caixa_rapido.services;

import com.caixa_rapido.dtos.cliente.ClienteRequest;
import com.caixa_rapido.dtos.cliente.ClienteResponse;
import com.caixa_rapido.models.Cliente;
import com.caixa_rapido.models.Compra;
import com.caixa_rapido.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;


    public ClienteResponse cadastrar(ClienteRequest dto) {
        if(repository.existsByCpf(dto.cpf()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");

        if(repository.existsByEmail(dto.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");

        var cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);

        return new ClienteResponse(repository.save(cliente));
    }

    public List<ClienteResponse> getAllResponse() {
        return repository.findAllResponse();
    }

    public Cliente getPorId(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com o id listado não existe");

        return repository.findById(id).get();
    }

    public ClienteResponse getResponsePorId(UUID id) {
        return new ClienteResponse(getPorId(id));
    }

    public ClienteResponse alterar(UUID id, ClienteRequest dto) {
        var cliente = getPorId(id);

        if(!dto.cpf().equals(cliente.getCpf()) && repository.existsByCpf(dto.cpf()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");

        if(!dto.email().equals(cliente.getEmail()) && repository.existsByEmail(dto.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");

        BeanUtils.copyProperties(dto, cliente);

        return new ClienteResponse(repository.save(cliente));
    }

    public void deletarPorId(UUID id) {
        repository.deleteById(id);
    }

    public void atualizarPontos(Compra compra) {
        var cliente = compra.getCliente();
        cliente.atualizarPontos(compra);
        repository.save(cliente);
    }
}
