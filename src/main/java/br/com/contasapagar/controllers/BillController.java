package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.BillDto;
import br.com.contasapagar.services.impl.BillServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping(value = "/v1")
public class BillController {
    private final BillServiceImpl billServiceImpl;

    @Autowired
    public BillController(BillServiceImpl billServiceImpl) {
        this.billServiceImpl = billServiceImpl;
    }

    @PostMapping("/bill")
    public ResponseEntity<BillDto> createBill(@Valid @RequestBody BillDto dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(this.billServiceImpl.create(dto));
    }

    @GetMapping("/bills")
    public ResponseEntity<Page<BillDto>> findAllBillPaged(
            Pageable pageable,
            @RequestParam(name = "description", defaultValue = "", required = false) String description) {
        return ResponseEntity.status(HttpStatus.OK).body(billServiceImpl.findAllBillsPaged(pageable, description));
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<BillDto> findBillById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(billServiceImpl.findBillById(id));
    }

    @DeleteMapping("/bills/{id}")
    public ResponseEntity<Void> deleteBillById(@PathVariable String id){
        billServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/bills/{id}")
    public ResponseEntity<BillDto> updatedBill(@PathVariable String id, @RequestBody BillDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(billServiceImpl.update(id, dto));
    }

}
