package br.com.compass.avaliacao.entities.dto.response;

import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.entities.dto.request.RequestItemDTO;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResponsePedidoDTO {
    private Long id;
    private String cpf;
    private Double total;
    private String status;
    private String statusDoPagamento;
    private String tipoDoPagamento;
    private Long pagamentoId;

    private List<ResponseItemDTO> itens;

}
