package br.com.compass.api4.controllers;

import br.com.compass.api4.dtos.request.RequestClienteDTO;
import br.com.compass.api4.dtos.response.ResponseClienteDTO;
import br.com.compass.api4.dtos.response.ResponseItemDTO;
import br.com.compass.api4.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping()
    public ResponseEntity<ResponseClienteDTO> post
            (@Valid @RequestBody RequestClienteDTO requestClienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseClienteDTO responseClienteDTO = clienteService.save(requestClienteDTO);
        URI uriBuilder = uriComponentsBuilder.path("/api/cliente/{id}").buildAndExpand(responseClienteDTO.getCpf()).toUri();
        return ResponseEntity.created(uriBuilder).body(responseClienteDTO);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseClienteDTO> get(@PathVariable String cpf) {
        ResponseClienteDTO responseClienteDTO = clienteService.getCliente(cpf);
        return ResponseEntity.ok(responseClienteDTO);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ResponseClienteDTO> put(@Valid @RequestBody RequestClienteDTO requestClienteDTO, @PathVariable String cpf) {
        ResponseClienteDTO responseClienteDTO = clienteService.update(requestClienteDTO, cpf);
        return ResponseEntity.ok(responseClienteDTO);
    }
}
