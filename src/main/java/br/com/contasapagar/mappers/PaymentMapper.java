package br.com.contasapagar.mappers;

import br.com.contasapagar.dtos.PaymentDto;
import br.com.contasapagar.entities.Payment;

public final class PaymentMapper {

    private PaymentMapper() {
    }

    public static Payment mapDtoToEntity(PaymentDto dto) {
        return Payment.builder().name(dto.getName()).build();
    }

    public static PaymentDto mapEntityToDto(Payment payment) {
        return PaymentDto.builder().id(payment.getId()).name(payment.getName()).build();
    }
}
