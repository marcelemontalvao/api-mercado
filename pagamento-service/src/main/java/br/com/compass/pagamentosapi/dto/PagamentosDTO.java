package br.com.compass.pagamentosapi.dto;

import lombok.Data;
@Data
public class PagamentosDTO {
    private Long pedidoId;
    private Double pedidoValorTotal;
    private String Cpf;
    private String tipoDoPagamento;
    private String numeroDoCartao;
    private String nomeDoCartao;
    private String codigoDeSeguranca;
    private String marca;
    private String mesDeExpiracao;
    private String anoDeExpiracao;
    private String moeda;
    private Double valor;
}
