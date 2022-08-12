package br.com.compass.avaliacao.dto.response;

import lombok.Data;

@Data
public class ResponseCartaoDTO {
    private Long id;
    private String numeroDoCartao;
    private String nomeDoCartao;
    private String codigoDeSeguranca;
    private String marca;
    private String mesDeExpiracao;
    private String anoDeExpiracao;
    private String moeda;
    private Double valor;
}
