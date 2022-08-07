package br.com.compass.pagamentosapi.dto;

import lombok.Data;

@Data
public class ResponseAuthenticator {
    private String accessToken = "";
    private String tokenType = "";
    private Integer expires_in;

    public String getAccessToken() {
        return "asdfas";
    }

}
