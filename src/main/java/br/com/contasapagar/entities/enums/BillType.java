package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum BillType {

    IN(1L), // Compensado
    OUT(0L); // Pagamento não realizado

    private final Long status;

    BillType(Long status) {
        this.status = status;
    }


}
