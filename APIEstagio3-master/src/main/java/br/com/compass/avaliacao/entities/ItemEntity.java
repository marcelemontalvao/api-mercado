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
public class ItemEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nome;
        private Date dataDeCriacao;
        private Date dataDeValidade;
        private Double valor;
        private String descricao;

        @ManyToMany(cascade = CascadeType.ALL)
        private List<OfertaEntity> ofertas;
}

