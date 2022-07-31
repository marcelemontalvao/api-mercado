package br.com.compass.avaliacao.entities.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseItemDTO {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeCriacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataDeValidade;
    private Double valor;
    private String descricao;
    private List<ResponseOfertaDTO> ofertas;
}
