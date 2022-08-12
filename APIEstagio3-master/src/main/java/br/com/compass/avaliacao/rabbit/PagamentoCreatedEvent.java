package br.com.compass.avaliacao.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoCreatedEvent {
    private String status;
    private Long id_pedido;
}
