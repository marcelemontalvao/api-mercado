package br.com.compass.pagamentosapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreatedEvent {
    private Long pedidoId;
    private Double pedidoValorTotal;
}
