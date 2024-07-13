package com.caixa_rapido.controllers;

import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoPostRequest;
import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoPutRequest;
import com.caixa_rapido.dtos.formaPagamento.FormaPagamentoResponse;
import com.caixa_rapido.models.FormaPagamento;
import com.caixa_rapido.services.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/formas-pagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {
    private final FormaPagamentoService service;

    @PostMapping
    public ResponseEntity<FormaPagamentoResponse> cadastrar(
            @RequestBody @Valid FormaPagamentoPostRequest dto
    ) {
        return status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoResponse>> getFormasPagamento() {
        var formasPagamento = service.getFormasPagamento();

        return formasPagamento.isEmpty()
                ? noContent().build()
                : ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> getFormaPagamentoPorId(@PathVariable UUID id) {
        return ok(service.getResponsePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> alterar(
            @PathVariable UUID id,
            @RequestBody @Valid FormaPagamentoPutRequest dto
    ) {
       return ok(service.alterar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable UUID id) {
        service.deletarPorId(id);
        return noContent().build();
    }
}
