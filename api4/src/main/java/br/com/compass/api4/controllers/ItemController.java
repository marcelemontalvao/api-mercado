package br.com.compass.api4.controllers;

import br.com.compass.api4.dtos.request.RequestItemDTO;
import br.com.compass.api4.dtos.response.ResponseItemDTO;
import br.com.compass.api4.repositorys.ItemRepository;
import br.com.compass.api4.services.ItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<ResponseItemDTO> post(@Valid @RequestBody RequestItemDTO requestItemDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseItemDTO responseItemDTO = itemService.save(requestItemDTO);
        URI uri = uriComponentsBuilder.path("/api/item/{id}").buildAndExpand(responseItemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseItemDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ResponseItemDTO>> get() {
        List<ResponseItemDTO> responseItemDTOS = itemService.getAll();
        return ResponseEntity.ok(responseItemDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseItemDTO> put(@Valid @RequestBody RequestItemDTO requestItemDTO, @PathVariable Long id) {
        ResponseItemDTO responseItemDTO = itemService.update(requestItemDTO, id);
        return ResponseEntity.ok(responseItemDTO);
    }
}
