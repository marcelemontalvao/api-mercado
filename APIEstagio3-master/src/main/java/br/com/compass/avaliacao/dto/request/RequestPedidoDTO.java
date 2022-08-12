package br.com.compass.avaliacao.dto.request;

import br.com.compass.avaliacao.entities.PedidoPagamentosEntity;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RequestPedidoDTO {
    @NotBlank(message = "Informe um CPF")
    @CPF
    private String cpf;

    @NotNull(message = "Informe o total")
    private Double total;

    @OneToOne(cascade = CascadeType.ALL, targetEntity= PedidoPagamentosEntity.class)
    private List<PedidoPagamentosEntity> pagamentoId;

    @NotNull
    @Size(min = 1)
    private List<@Valid RequestItemDTO> itens;

    @NotNull
    private @Valid RequestCartaoDTO pagamento;
}
