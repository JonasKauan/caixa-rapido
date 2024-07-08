package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.cliente.ClienteRequest;
import com.caixa_rapido.dtos.cliente.ClienteResponse;
import com.caixa_rapido.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClienteRequest dto) {
        return status(HttpStatus.CREATED).body(new ClienteResponse(service.cadastrar(dto)));
    }
}
