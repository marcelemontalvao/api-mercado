package br.com.compass.pagamentosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
@Data

public class Authorization {
    @JsonProperty("authorization_code")
    private String authorizationCode;

    @JsonProperty("authorized_at")
    private LocalDateTime authorizedAt;

    @JsonProperty("reason_code")
    private Integer reasonCode;

    @JsonProperty("reason_message")
    private String reasonMessage;
}
