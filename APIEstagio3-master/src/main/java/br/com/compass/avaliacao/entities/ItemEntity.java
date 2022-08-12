package br.com.compass.avaliacao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido_itens")
public class ItemEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private Date dataDeCriacao;
        private Date dataDeValidade;
        private Double valor;
        private String descricao;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "item_id")
        private List<OfertaEntity> ofertas;
}

