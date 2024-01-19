package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.PaymentDto;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.mappers.PaymentMapper;
import br.com.contasapagar.repositories.PaymentRepository;
import br.com.contasapagar.services.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    public PaymentServiceImpl(PaymentRepository repository){
        this.paymentRepository = repository;
    }
    @Override
    @Transactional
    public PaymentDto create(PaymentDto dto) {
        Optional<Payment> paymentAlreadyExists = paymentRepository.findByName(dto.getName());
        if(paymentAlreadyExists.isPresent()) throw new RuntimeException("This payment is already exists");
        Payment createdPayment = PaymentMapper.mapDtoToEntity(dto);
        createdPayment = paymentRepository.save(createdPayment);
        return PaymentMapper.mapEntityToDto(createdPayment);
    }
}
