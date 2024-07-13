package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.cliente.ClientePutRequest;
import com.caixa_rapido.dtos.cliente.ClientePostRequest;
import com.caixa_rapido.dtos.cliente.ClienteResponse;
import com.caixa_rapido.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClientePostRequest dto) {
        return status(HttpStatus.CREATED).body(new ClienteResponse(service.cadastrar(dto)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getClientes() {
        var clientes = service.getAllResponse();

        return clientes.isEmpty()
                ? notFound().build()
                : ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getClientePorId(@PathVariable UUID id) {
        return ok(new ClienteResponse(service.getPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> alterar(
        @PathVariable UUID id,
        @Valid @RequestBody ClientePutRequest dto
    ) {
        return ok(new ClienteResponse(service.alterar(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
