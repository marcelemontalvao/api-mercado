package br.com.compass.api4.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class RequestCartoesDTO {

    @NotNull
    private Integer numero;
    @NotBlank
    private String codigo;
    @NotBlank
    private String mesValidade;
    @NotBlank
    private String anoValidade;
    @NotBlank
    private String marca;



}
