package br.com.compass.avaliacao.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class RequestItemDTO {
    @NotBlank(message = "Informe um nome")
    private String nome;

    @NotNull(message = "Informe uma data de criação")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat
    private Date dataDeCriacao;

    @NotNull(message = "Informe uma data de validade")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeValidade;

    @NotNull(message = "Informe um valor")
    private Double valor;

    @NotBlank(message = "Informe uma descrição")
    private String descricao;

    private List<RequestOfertaDTO> ofertas;

}
