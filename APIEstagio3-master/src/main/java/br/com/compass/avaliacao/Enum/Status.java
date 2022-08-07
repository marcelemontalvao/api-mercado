package br.com.compass.avaliacao.Enum;

public enum Status {
   EM_ANDAMENTO("Em Andamento"),
   FINALIZADO("Finalizado"),
   CANCELADO("Cancelado");

   private String status;

    Status(String status) {
        this.status = status;
    }
}
