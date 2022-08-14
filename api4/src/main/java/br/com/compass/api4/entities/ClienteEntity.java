package br.com.compass.api4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class ClienteEntity {
    @Id
    private String cpf;
    private String nome;
    private LocalDateTime dataCriacao;

    @OneToMany
    private List<CartoesEntity> cartoesEntityList;
}
