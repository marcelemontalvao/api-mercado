package br.com.compass.avaliacao.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RequestOfertaDTO {

    @NotBlank(message = "Informe um nome")
    private String nome;

    @NotNull(message = "Informe uma data de validação")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeCriacao;

    @NotNull(message = "Informe uma data de validade")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeValidade;

    @NotNull(message = "Informe um desconto")
    private Double desconto;

    @NotBlank(message = "Informe uma descrição")
    private String descricao;

}
