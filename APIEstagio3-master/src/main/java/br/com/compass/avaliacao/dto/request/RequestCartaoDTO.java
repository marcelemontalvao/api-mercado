package br.com.compass.avaliacao.dto.request;

import lombok.Data;

@Data
public class RequestCartaoDTO {
    private String numeroDoCartao;
    private String nomeDoCartao;
    private String codigoDeSeguranca;
    private String marca;
    private String mesDeExpiracao;
    private String anoDeExpiracao;
    private String moeda;
    private Double valor;
}
