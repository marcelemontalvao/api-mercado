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
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private Double total;
    private String status = "EM_ANDAMENTO";
    private String statusDoPagamento = "PROCESSANDO";
    private String tipoDoPagamento = "CREDIT_CARD";

    @OneToOne(cascade = CascadeType.ALL, targetEntity= PedidoPagamentosEntity.class)
    private PedidoPagamentosEntity pagamento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemEntity> itens;

}
