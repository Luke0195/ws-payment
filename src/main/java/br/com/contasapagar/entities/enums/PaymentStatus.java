package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    CHECK(0),
    MONEY(1),
    PIX(2);

    private final Integer status;

    PaymentStatus(Integer status) {
        this.status = status;
    }


}
