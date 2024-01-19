package br.com.contasapagar.services;

import br.com.contasapagar.dtos.PaymentDto;

public interface PaymentService {

    PaymentDto create(PaymentDto dto);
}
