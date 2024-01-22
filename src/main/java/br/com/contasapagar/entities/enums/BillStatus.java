package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum BillStatus {

    INT(1),
    OUT(0);
    private final Integer status;

    BillStatus(Integer status) {
        this.status = status;
    }
}
