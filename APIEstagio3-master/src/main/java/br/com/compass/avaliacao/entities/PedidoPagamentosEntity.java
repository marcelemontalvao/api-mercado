package br.com.compass.avaliacao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido_pagamentos")
public class PedidoPagamentosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numeroDoCartao;
    private String nomeDoCartao;
    private String codigoDeSeguranca;
    private String marca;
    private String mesDeExpiracao;
    private Integer anoDeExpiracao;
    private String moeda;
    private Double valor;
}
