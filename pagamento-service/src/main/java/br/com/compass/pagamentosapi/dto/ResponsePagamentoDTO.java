package br.com.compass.pagamentosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponsePagamentoDTO {
    @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("received_at")
    private String received_at;

    @JsonProperty("authorization")
    private Authorization authorization;

}
