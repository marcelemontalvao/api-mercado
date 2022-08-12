package br.com.compass.avaliacao.rabbit;

import br.com.compass.avaliacao.dto.response.ResponsePedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCreatedProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(ResponsePedidoDTO responsePedidoDTO) {
        PedidoCreatedEvent pedidoCreatedEvent = new PedidoCreatedEvent();
        pedidoCreatedEvent.setPedidoId(responsePedidoDTO.getId());
        pedidoCreatedEvent.setCpf(responsePedidoDTO.getCpf());
        pedidoCreatedEvent.setMarca(responsePedidoDTO.getPagamento().getMarca());
        pedidoCreatedEvent.setMoeda(responsePedidoDTO.getPagamento().getMoeda());
        pedidoCreatedEvent.setValor(responsePedidoDTO.getPagamento().getValor());
        pedidoCreatedEvent.setCodigoDeSeguranca(responsePedidoDTO.getPagamento().getCodigoDeSeguranca());
        pedidoCreatedEvent.setAnoDeExpiracao(responsePedidoDTO.getPagamento().getAnoDeExpiracao());
        pedidoCreatedEvent.setMesDeExpiracao(responsePedidoDTO.getPagamento().getMesDeExpiracao());
        pedidoCreatedEvent.setNomeDoCartao(responsePedidoDTO.getPagamento().getNomeDoCartao());
        pedidoCreatedEvent.setTipoDoPagamento(responsePedidoDTO.getTipoDoPagamento());
        pedidoCreatedEvent.setNumeroDoCartao(responsePedidoDTO.getPagamento().getNumeroDoCartao());
        pedidoCreatedEvent.setPedidoValorTotal(responsePedidoDTO.getTotal());

        rabbitTemplate.convertAndSend("pedidos.v1.pedido-created", pedidoCreatedEvent);
    }
}
