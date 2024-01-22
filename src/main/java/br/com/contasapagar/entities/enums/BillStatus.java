package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum BillStatus {

    INT(1L),
    OUT(0L);
    private final Long status;

    BillStatus(Long status) {
        this.status = status;
    }
}
