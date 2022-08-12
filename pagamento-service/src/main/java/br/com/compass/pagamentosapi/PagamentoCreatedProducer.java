package br.com.compass.pagamentosapi;

import br.com.compass.pagamentosapi.dto.PagamentosDTO;
import br.com.compass.pagamentosapi.dto.ResponsePagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoCreatedProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(ResponsePagamentoDTO responsePedidoDTO, PagamentosDTO pagamento) {
        PagamentoCreatedEvent pagamentoCreatedEvent = new PagamentoCreatedEvent();

        pagamentoCreatedEvent.setStatus(responsePedidoDTO.getStatus());
        pagamentoCreatedEvent.setId_pedido(pagamento.getPedidoId());

        rabbitTemplate.convertAndSend("pagamentos.v1.pagamento-created", pagamentoCreatedEvent);
    }
}
