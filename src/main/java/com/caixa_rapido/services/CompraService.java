package com.caixa_rapido.services;

import com.caixa_rapido.dtos.compra.CompraPutRequest;
import com.caixa_rapido.dtos.compra.CompraResponse;
import com.caixa_rapido.models.Compra;
import com.caixa_rapido.models.ProdutoCompra;
import com.caixa_rapido.repositories.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final ClienteService clienteService;

    public Compra cadastrar(UUID fkCliente) {
        var compra = new Compra();
        if(fkCliente != null) compra.setCliente(clienteService.getPorId(fkCliente));
        return compraRepository.save(compra);
    }

    public List<CompraResponse> getAllResponse() {
        return compraRepository.findAllResponse();
    }

    public Compra getPorId(UUID id) {
        if(!compraRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra com o id listado não listado");

        return compraRepository.findById(id).get();
    }

    public Compra alterar(UUID id, CompraPutRequest dto) {
        var compra = getPorId(id);

        compra.setCliente(clienteService.getPorId(dto.fkCliente()));
        BeanUtils.copyProperties(dto, compra);

        return compraRepository.save(compra);
    }

    public void deletaPorId(UUID id) {
        if(!compraRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra com o id listado não listado");

        compraRepository.deleteById(id);
    }


    public Compra finalizarCompra(UUID id) {
        var compra = getPorId(id);

        compra.calcularTotal();

        return compraRepository.save(compra);
    }
}
