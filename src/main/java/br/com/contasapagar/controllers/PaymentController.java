package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.PaymentDto;
import br.com.contasapagar.services.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/v1/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImplementation;

    public PaymentController(PaymentServiceImpl paymentServiceImplementation){
        this.paymentServiceImplementation = paymentServiceImplementation;
    }


    @PostMapping
    public ResponseEntity<PaymentDto> insertPayment(@Valid @RequestBody PaymentDto dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(paymentServiceImplementation.create(dto));
    }
}
