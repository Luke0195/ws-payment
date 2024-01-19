package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.PaymentDto;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.mappers.PaymentMapper;
import br.com.contasapagar.repositories.PaymentRepository;
import br.com.contasapagar.services.PaymentService;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        if(paymentAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This payment is already exists");
        Payment createdPayment = PaymentMapper.mapDtoToEntity(dto);
        createdPayment = paymentRepository.save(createdPayment);
        return PaymentMapper.mapEntityToDto(createdPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> findAll() {
        List<Payment> paymentsTypes = paymentRepository.findAll();
        return paymentsTypes.stream().map(PaymentMapper::mapEntityToDto).toList();
    }
}
