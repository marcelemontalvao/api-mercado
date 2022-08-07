package br.com.compass.avaliacao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private Double total;
    private String status;
    private String statusDoPagamento;
    private String tipoDoPagamento;

    @OneToOne(cascade = CascadeType.ALL, targetEntity= PedidoPagamentosEntity.class)
    private List<PedidoPagamentosEntity> pagamentoId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ItemEntity> itens;
}
