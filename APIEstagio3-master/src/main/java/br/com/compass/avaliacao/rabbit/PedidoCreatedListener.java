package br.com.compass.avaliacao.rabbit;

import br.com.compass.avaliacao.Enum.Status;
import br.com.compass.avaliacao.Enum.StatusDoPagamento;
import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.exceptions.PedidoNotFoundException;
import br.com.compass.avaliacao.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoCreatedListener {

    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "pagamentos.v1.pagamento-created")
    public void onPedidoCreated(PagamentoCreatedEvent event) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(event.getId_pedido()).orElseThrow(PedidoNotFoundException::new);
        if (event.getStatus().equals("APPROVED")) {
            pedidoEntity.setStatus(Status.FINALIZADO.name());
            pedidoEntity.setStatusDoPagamento(StatusDoPagamento.APROVADO.name());
        } else {
            pedidoEntity.setStatus(Status.CANCELADO.name());
            pedidoEntity.setStatusDoPagamento(StatusDoPagamento.REJEITADO.name());
        }
        pedidoRepository.save(pedidoEntity);
    }

}
