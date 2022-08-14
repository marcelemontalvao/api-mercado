package br.com.compass.api4.controllers;

import br.com.compass.api4.dtos.request.RequestCartoesDTO;
import br.com.compass.api4.dtos.response.ResponseCartoesDTO;
import br.com.compass.api4.services.CartoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public class CartoesController {
    @Autowired
    private CartoesService cartoesService;

    @GetMapping("/api/cliente/{cpf}/cartoes")
    public ResponseEntity<List<ResponseCartoesDTO>> get(@PathVariable String cpf) {
        List<ResponseCartoesDTO> responseCartoesDTOs = cartoesService.getAll(cpf);
        return ResponseEntity.ok(responseCartoesDTOs);
    }

    @PostMapping("/api/cliente/{cpf}/cartoes")
    public ResponseEntity<ResponseCartoesDTO> post
            (@Valid @RequestBody RequestCartoesDTO requestCartoesDTO, @PathVariable String cpf, UriComponentsBuilder uriComponentsBuilder) {
        ResponseCartoesDTO responseCartoesDTO = cartoesService.save(requestCartoesDTO, cpf);
        URI uri = uriComponentsBuilder.path("/api/cliente/{cpf}/cartoes/{id}").buildAndExpand(cpf,responseCartoesDTO.getId()).toUri();
        return ResponseEntity.ok(responseCartoesDTO);
    }

    @PutMapping()
    public ResponseEntity<ResponseCartoesDTO> put(@Valid @RequestBody RequestCartoesDTO requestCartoesDTO, @PathVariable Long id) {
        ResponseCartoesDTO responseCartoesDTO = cartoesService.update(requestCartoesDTO, id);
        return ResponseEntity.ok(responseCartoesDTO);
    }
}
