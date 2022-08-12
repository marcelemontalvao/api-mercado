package br.com.compass.avaliacao.controller;

import br.com.compass.avaliacao.rabbit.PedidoCreatedProducer;
import br.com.compass.avaliacao.dto.request.RequestPedidoDTO;
import br.com.compass.avaliacao.dto.response.ResponsePedidoDTO;
import br.com.compass.avaliacao.repository.PedidoRepository;
import br.com.compass.avaliacao.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedido/")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoCreatedProducer pedidoCreatedProducer;

    @GetMapping()
    public ResponseEntity<List<ResponsePedidoDTO>> get
            (@RequestParam(required = false) String cpf,
             @RequestParam(required = false, defaultValue = "id" ) String ordenar){
        List<ResponsePedidoDTO> responsePedidoDTOS = pedidoService.get(cpf, ordenar);
        return ResponseEntity.ok(responsePedidoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDTO> getPedidoById(@PathVariable Long id) {
        ResponsePedidoDTO responsePedidoDTO = pedidoService.getById(id);
        return ResponseEntity.ok(responsePedidoDTO);
    }

    @PostMapping
    public ResponseEntity<ResponsePedidoDTO> postPedido( @Valid @RequestBody RequestPedidoDTO request, UriComponentsBuilder uriBuilder) {
        ResponsePedidoDTO responsePedido = pedidoService.save(request);
        pedidoCreatedProducer.sendMessage(responsePedido);
        URI uri = uriBuilder.path("/api/pedidos/{id}").buildAndExpand(responsePedido.getId()).toUri();
        return ResponseEntity.created(uri).body(responsePedido);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestPedidoDTO request, @PathVariable Long id) {
        pedidoService.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
