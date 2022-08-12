package br.com.compass.avaliacao.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreatedEvent {
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
