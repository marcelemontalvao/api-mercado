package br.com.compass.api4.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseItemDTO {
    private Long id;
    private String nome;
    private Date dataDeValidade;
    private Double valor;
    private String descricao;
    private Integer estoque;
    private String skuid;
}
