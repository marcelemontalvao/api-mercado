package br.com.compass.pagamentosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPagamentoDTO {

    @JsonProperty("seller_id")
    private String sellerId = "a61fc0ab-9c38-472c-a4e2-41e5e4492ec8";

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("payment_type")
    private String paymentType = "CREDIT_CARD";

    @JsonProperty("currency")
    private String currency = "BRL";

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("card")
    private Card card;

}
