package br.com.compass.avaliacao.entities.dto.request;

import br.com.compass.avaliacao.entities.ItemEntity;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
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

    private List<RequestItemDTO> itens;
}
