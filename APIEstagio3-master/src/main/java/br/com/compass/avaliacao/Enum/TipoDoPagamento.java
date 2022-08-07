package br.com.compass.avaliacao.Enum;

public enum TipoDoPagamento {
    CREDIT_CARD("Credit Card"),
    PIX("Pix"),
    BANK_PAYMENT_SLIP("Bank Payment Slip");

    private String tipoDoPagamento;
    TipoDoPagamento(String tipoDoPagamento) {
        this.tipoDoPagamento = tipoDoPagamento;
    }
}
