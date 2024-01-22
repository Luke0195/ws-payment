package br.com.contasapagar.services;

import br.com.contasapagar.dtos.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto create(PaymentDto dto);

    List<PaymentDto> findAll();
}
