package br.com.compass.api4.dtos.response;

import lombok.Data;

@Data
public class ResponseCartoesDTO {
    private Long id;
    private int numero;
    private String codigo;
    private String mesValidade;
    private String anoValidade;
    private String marca;
}
