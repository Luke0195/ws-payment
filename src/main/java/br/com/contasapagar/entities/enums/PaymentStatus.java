package br.com.contasapagar.entities.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    CHECK(0L),
    MONEY(1L),
    PIX(2L);

    private final Long status;

    PaymentStatus(Long status) {
        this.status = status;
    }


}
