package br.com.compass.avaliacao.entities.dto.request;

import br.com.compass.avaliacao.entities.ItemEntity;
import br.com.compass.avaliacao.entities.PedidoEntity;
import br.com.compass.avaliacao.entities.PedidoPagamentosEntity;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestPedidoDTO {
    @NotBlank(message = "Informe um CPF")
    @CPF
    private String cpf;
    @NotNull(message = "Informe o total")
    private Double total;
    private String status;
    private String statusDoPagamento;
    private String tipoDoPagamento;

    @OneToOne(cascade = CascadeType.ALL, targetEntity= PedidoPagamentosEntity.class)
    private List<PedidoPagamentosEntity> pagamentoId;

    private List<RequestItemDTO> itens;
}
