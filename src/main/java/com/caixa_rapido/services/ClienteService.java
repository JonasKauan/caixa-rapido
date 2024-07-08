package com.caixa_rapido.services;

import com.caixa_rapido.dtos.cliente.ClienteRequest;
import com.caixa_rapido.models.Cliente;
import com.caixa_rapido.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;


    public Cliente cadastrar(ClienteRequest dto) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        return repository.save(cliente);
    }
}
