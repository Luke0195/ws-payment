package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.services.impl.ClientServiceImpl;
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
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientDto> insertClient(@Valid @RequestBody ClientDto dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(this.clientServiceImpl.createClient(dto));
    }

    @GetMapping("/clients")
    public ResponseEntity<Page<ClientDto>> findAllClientsPaged(
            Pageable pageable,
            @RequestParam(name = "name", defaultValue = "", required = false) String name
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientServiceImpl.findAllPaged(pageable, name));
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientServiceImpl.findClientById(id));
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable String id){
        this.clientServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
