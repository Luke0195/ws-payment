package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.BillDto;
import br.com.contasapagar.services.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class BillController {

    private final BillServiceImpl billServiceImpl;

    @Autowired
    public BillController(BillServiceImpl billServiceImpl){
        this.billServiceImpl = billServiceImpl;
    }

    public ResponseEntity<BillDto> createBill(){}
}
