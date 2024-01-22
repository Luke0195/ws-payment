package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum BillType {

    IN(1), // Compensado
    OUT(0); // Pagamento não realizado

    private final Integer status;

    BillType(Integer status) {
        this.status = status;
    }


}
