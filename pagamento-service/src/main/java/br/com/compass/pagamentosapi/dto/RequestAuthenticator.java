package br.com.compass.pagamentosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestAuthenticator {
    @JsonProperty("client_id")
    private String clientId = "client_id_marcele";

    @JsonProperty("api_key")
    private String apiKey = "3b50181f-bac4-459b-aa3d-ba17ee673080";
}
