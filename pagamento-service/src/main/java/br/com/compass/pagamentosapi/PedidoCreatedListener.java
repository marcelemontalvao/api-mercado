package br.com.compass.pagamentosapi;

import br.com.compass.pagamentosapi.dto.PagamentosDTO;
import br.com.compass.pagamentosapi.dto.ResponsePagamentoDTO;
import br.com.compass.pagamentosapi.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoCreatedListener {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoCreatedProducer pagamentoCreatedProducer;


    @RabbitListener(queues = "pedidos.v1.pedido-created")
    public void PedidoCreated(PagamentosDTO pagamentosDTO){
    ResponsePagamentoDTO responsePagamentoDTO = pagamentoService.sendGateway(pagamentosDTO);
    pagamentoCreatedProducer.sendMessage(responsePagamentoDTO, pagamentosDTO);
    }


}
