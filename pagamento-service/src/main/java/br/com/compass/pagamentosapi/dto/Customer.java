package br.com.compass.pagamentosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Customer {

    @JsonProperty("document_type")
    private String DocumentType = "CPF";


    @JsonProperty("document_number")
    private String DocumentNumber;


}
