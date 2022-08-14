package br.com.compass.api4.dtos.request;

import br.com.compass.api4.entities.CartoesEntity;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
public class RequestClienteDTO {
    @NotEmpty
    @CPF()
    private String cpf;
    @NotBlank()
    private String nome;
    private List<CartoesEntity> cartoesEntityList;

}
