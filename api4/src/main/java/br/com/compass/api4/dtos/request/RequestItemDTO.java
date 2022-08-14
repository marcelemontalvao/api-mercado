package br.com.compass.api4.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RequestItemDTO {
    @NotBlank
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeValidade;
    private Double valor;
    @NotBlank
    private String descricao;
    @NotNull
    private Integer estoque;
    private String skuid;
}
