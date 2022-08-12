package br.com.compass.avaliacao.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePedidoDTO {
    private Long id;
    private String cpf;
    private Double total;
    private String status;
    private String statusDoPagamento;
    private String tipoDoPagamento;

    private List<ResponseItemDTO> itens;

    private ResponseCartaoDTO pagamento;
}
