package br.com.compass.avaliacao.Enum;

public enum StatusDoPagamento {
    PROCESSANDO("Processando"),
    REJEITADO("Rejeitado"),
    APROVADO("Aprovado");

    private String statusDoPagamento;

    StatusDoPagamento(String statusDoPagamento) {
        this.statusDoPagamento = statusDoPagamento;
    }
}
