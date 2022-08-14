package br.com.compass.api4.dtos.response;

import br.com.compass.api4.entities.CartoesEntity;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class ResponseClienteDTO {
    private String cpf;
    private String nome;
    private Date dataCriacao;
    private List<CartoesEntity> cartoesEntityList;
}
