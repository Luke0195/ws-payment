package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.PaymentDto;
import br.com.contasapagar.services.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/v1")
public class PaymentController {
    private final PaymentServiceImpl paymentServiceImplementation;
    public PaymentController(PaymentServiceImpl paymentServiceImplementation){
        this.paymentServiceImplementation = paymentServiceImplementation;
    }
    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> insertPayment(@Valid @RequestBody PaymentDto dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(paymentServiceImplementation.create(dto));
    }

    @GetMapping(value = "/payments")
    public ResponseEntity<List<PaymentDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServiceImplementation.findAll());
    }
}
